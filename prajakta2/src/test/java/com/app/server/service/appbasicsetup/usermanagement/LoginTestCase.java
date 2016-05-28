package com.app.server.service.appbasicsetup.usermanagement;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import com.app.config.WebConfigExtended;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.appbasicsetup.usermanagement.LoginRepository;
import com.app.shared.appbasicsetup.usermanagement.Login;
import org.springframework.beans.factory.annotation.Autowired;
import com.athena.server.pluggable.utils.helper.RuntimeLogInfoHelper;
import com.athena.server.pluggable.utils.helper.EntityValidatorHelper;
import com.app.server.service.RandomValueGenerator;
import java.util.HashMap;
import java.util.List;
import com.spartan.healthmeter.entity.scheduler.ArtMethodCallStack;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.junit.BeforeClass;
import org.junit.Before;
import org.junit.After;
import org.springframework.mock.web.MockServletContext;
import com.spartan.pluggable.logger.api.LogManagerFactory;
import com.athena.server.pluggable.utils.AppLoggerConstant;
import com.spartan.pluggable.logger.api.LogManager;
import com.spartan.pluggable.logger.event.RequestHeaderBean;
import com.spartan.pluggable.logger.api.RuntimeLogUserInfoBean;
import com.athena.server.pluggable.interfaces.CommonEntityInterface.RECORD_TYPE;
import org.junit.Test;
import com.app.shared.appbasicsetup.usermanagement.User;
import com.app.server.repository.appbasicsetup.usermanagement.UserRepository;
import com.app.shared.appbasicsetup.usermanagement.UserAccessLevel;
import com.app.server.repository.appbasicsetup.usermanagement.UserAccessLevelRepository;
import com.app.shared.appbasicsetup.usermanagement.UserAccessDomain;
import com.app.server.repository.appbasicsetup.usermanagement.UserAccessDomainRepository;
import com.app.shared.appbasicsetup.usermanagement.PassRecovery;
import com.app.shared.appbasicsetup.usermanagement.Question;
import com.app.server.repository.appbasicsetup.usermanagement.QuestionRepository;
import com.app.shared.appbasicsetup.usermanagement.UserData;
import com.app.shared.organization.contactmanagement.CoreContacts;
import com.app.server.repository.organization.contactmanagement.CoreContactsRepository;
import com.app.shared.organization.contactmanagement.Gender;
import com.app.server.repository.organization.contactmanagement.GenderRepository;
import com.app.shared.organization.contactmanagement.Title;
import com.app.server.repository.organization.contactmanagement.TitleRepository;
import com.app.shared.organization.locationmanagement.Language;
import com.app.server.repository.organization.locationmanagement.LanguageRepository;
import com.app.shared.organization.locationmanagement.Timezone;
import com.app.server.repository.organization.locationmanagement.TimezoneRepository;
import com.app.shared.organization.contactmanagement.CommunicationData;
import com.app.shared.organization.contactmanagement.CommunicationType;
import com.app.server.repository.organization.contactmanagement.CommunicationTypeRepository;
import com.app.shared.organization.contactmanagement.CommunicationGroup;
import com.app.server.repository.organization.contactmanagement.CommunicationGroupRepository;
import com.app.shared.organization.locationmanagement.Address;
import com.app.server.repository.organization.locationmanagement.AddressRepository;
import com.app.shared.organization.locationmanagement.Country;
import com.app.server.repository.organization.locationmanagement.CountryRepository;
import com.app.shared.organization.locationmanagement.City;
import com.app.server.repository.organization.locationmanagement.CityRepository;
import com.app.shared.organization.locationmanagement.State;
import com.app.server.repository.organization.locationmanagement.StateRepository;
import com.app.shared.organization.locationmanagement.AddressType;
import com.app.server.repository.organization.locationmanagement.AddressTypeRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = WebConfigExtended.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class LoginTestCase extends EntityTestCriteria {

    @Autowired
    private LoginRepository<Login> loginRepository;

    @Autowired
    private RuntimeLogInfoHelper runtimeLogInfoHelper;

    @Autowired
    private EntityValidatorHelper<Object> entityValidator;

    private RandomValueGenerator valueGenerator = new RandomValueGenerator();

    private static HashMap<String, Object> map = new HashMap<String, Object>();

    private static List<EntityTestCriteria> entityContraint;

    @Autowired
    private ArtMethodCallStack methodCallStack;

    protected MockHttpSession session;

    protected MockHttpServletRequest request;

    protected MockHttpServletResponse response;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        MockServletContext mockServletContext = new MockServletContext("file:src/main/webapp");
        try {
            String _path = mockServletContext.getRealPath("/WEB-INF/conf/");
            LogManagerFactory.createLogManager(_path, AppLoggerConstant.LOGGER_ID);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void startSession() {
        session = new MockHttpSession();
    }

    protected void endSession() {
        session.clearAttributes();
        session.invalidate();
        session = null;
    }

    protected void startRequest() {
        request = new MockHttpServletRequest();
        request.setSession(session);
        org.springframework.web.context.request.RequestContextHolder.setRequestAttributes(new org.springframework.web.context.request.ServletRequestAttributes(request));
    }

    protected void endRequest() {
        ((org.springframework.web.context.request.ServletRequestAttributes) org.springframework.web.context.request.RequestContextHolder.getRequestAttributes()).requestCompleted();
        org.springframework.web.context.request.RequestContextHolder.resetRequestAttributes();
        request = null;
    }

    @Before
    public void before() {
        startSession();
        startRequest();
        setBeans();
    }

    @After
    public void after() {
        endSession();
        endRequest();
    }

    private void setBeans() {
        runtimeLogInfoHelper.createRuntimeLogUserInfo("customer", "AAAAA", request.getRemoteHost());
        org.junit.Assert.assertNotNull(runtimeLogInfoHelper);
        methodCallStack.setRequestId(java.util.UUID.randomUUID().toString().toUpperCase());
        entityContraint = addingListOfFieldForNegativeTesting();
        runtimeLogInfoHelper.setRequestHeaderBean(new RequestHeaderBean(new RuntimeLogUserInfoBean("AAAA", "AAAA", request.getRemoteHost(), 0, 0, 0), "", methodCallStack.getRequestId()));
    }

    private Login createLogin(Boolean isSave) throws Exception {
        User user = new User();
        user.setMultiFactorAuthEnabled(1);
        user.setIsDeleted(1);
        user.setUserAccessCode(7288);
        UserAccessLevel useraccesslevel = new UserAccessLevel();
        useraccesslevel.setUserAccessLevel(valueGenerator.getRandomInteger(99999, 0));
        useraccesslevel.setLevelName("xFpomZwISmSQHFK7TymQr3XqoGa3nN9I4r7Zk3rb8TwIaD9bQZ");
        useraccesslevel.setLevelDescription("jT3G7auLhSTBCZwA1X1naCuWgMAzWbOwAnweBuoSEpbd3i3lZD");
        useraccesslevel.setLevelHelp("z8j9JUS5ccaYwMT0EMp2UjULG31MS8pYOR0OB6R3rmjfRx2y7o");
        useraccesslevel.setLevelIcon("eJB871wEF7e7rNuHMcQPnzQlGQEjfBXGLMyUXU6guaH6NvnOvU");
        UserAccessLevel UserAccessLevelTest = new UserAccessLevel();
        if (isSave) {
            UserAccessLevelTest = useraccesslevelRepository.save(useraccesslevel);
            map.put("UserAccessLevelPrimaryKey", useraccesslevel._getPrimarykey());
        }
        UserAccessDomain useraccessdomain = new UserAccessDomain();
        useraccessdomain.setUserAccessDomain(valueGenerator.getRandomInteger(99999, 0));
        useraccessdomain.setDomainName("GZr9ejxqhXgmRi1hz70SK19jdIVzRvpUSsdJlNnVEpJe4J9v2F");
        useraccessdomain.setDomainHelp("qwLzjqf8J3FdJTV6DxraeFWxSlVrAwsovvIYpvvDEynOBrRQ73");
        useraccessdomain.setDomainIcon("qJ1jRw0TsmtmLFDyBICj5N9faHyEiah9Lu6azaycvHPfYRwKT5");
        useraccessdomain.setDomainDescription("f2IvuWLNDrhcZ4FCbMUbAJSF1WNsetkNrdDU8ceEVUJh2fVEhM");
        UserAccessDomain UserAccessDomainTest = new UserAccessDomain();
        if (isSave) {
            UserAccessDomainTest = useraccessdomainRepository.save(useraccessdomain);
            map.put("UserAccessDomainPrimaryKey", useraccessdomain._getPrimarykey());
        }
        user.setMultiFactorAuthEnabled(1);
        user.setIsDeleted(1);
        user.setUserAccessCode(52007);
        user.setUserAccessLevelId((java.lang.String) UserAccessLevelTest._getPrimarykey()); /* ******Adding refrenced table data */
        user.setPasswordExpiryDate(new java.sql.Timestamp(1464425362257l));
        user.setLastPasswordChangeDate(new java.sql.Timestamp(1464425362257l));
        user.setUserAccessDomainId((java.lang.String) UserAccessDomainTest._getPrimarykey()); /* ******Adding refrenced table data */
        user.setIsLocked(1);
        user.setGenTempOneTimePassword(1);
        user.setAllowMultipleLogin(1);
        user.setSessionTimeout(1960);
        user.setPasswordAlgo("KYLxqpRrk2tcq6NViYBlhVb6FnOcZzEszvR2YtzmOZfleW81L3");
        user.setChangePasswordNextLogin(1);
        java.util.List<PassRecovery> listOfPassRecovery = new java.util.ArrayList<PassRecovery>();
        PassRecovery passrecovery = new PassRecovery();
        Question question = new Question();
        question.setQuestionIcon("zPYYax9hojgkgcb0onL0a4OuX07r4pE8i6wCOf4mplHAOhiAOx");
        question.setQuestionDetails("XviJ7hlB4y");
        question.setQuestion("jemJ2cF3jJ4uGYwsITyozq2ZlhHTyyqUr6jyVshaTe4VjgfEok");
        question.setLevelid(3);
        Question QuestionTest = new Question();
        if (isSave) {
            QuestionTest = questionRepository.save(question);
            map.put("QuestionPrimaryKey", question._getPrimarykey());
        }
        passrecovery.setUser(user);
        passrecovery.setAnswer("0HRjOq2AWt1LkTsUmSncWpH9dkb9A2aRng9ExBNrAFpCoxHzTG");
        passrecovery.setQuestionId((java.lang.String) QuestionTest._getPrimarykey()); /* ******Adding refrenced table data */
        listOfPassRecovery.add(passrecovery);
        user.addAllPassRecovery(listOfPassRecovery);
        UserData userdata = new UserData();
        userdata.setOneTimePassword("1PMTpKLkfEeWJbDUnRj5FikA6eio9K3B");
        userdata.setOneTimePasswordGenDate(new java.sql.Timestamp(1464425363586l));
        userdata.setOneTimePassword("PWstpimUyXKz2YBUUfLFXopn82xI2BSL");
        userdata.setOneTimePasswordGenDate(new java.sql.Timestamp(1464425363707l));
        userdata.setUser(user);
        userdata.setOneTimePasswordExpiry(6);
        userdata.setPassword("UcRahDFCUGB2Pbi2aJhAYTlGYfjjHZt1Eu9l9EhATpxPvEspPi");
        userdata.setLast5Passwords("od1HgvUwIS18il3kqxcrqEXzZjExTNJf72DJOagpfERMPpFDyW");
        user.setUserData(userdata);
        CoreContacts corecontacts = new CoreContacts();
        corecontacts.setNativeLastName("c4QKErfdZyia9jgPzjrcSGPTaf3BiqBJ4t6sOV7y2JvnBBuQsx");
        corecontacts.setNativeMiddleName("8x2oeiX8R4y0WLmiV4hsPHCwnOq5d8Ls2T0C8Azo1QkCYjTcd2");
        corecontacts.setPhoneNumber("59rINULpkkBwcx9SEMkl");
        corecontacts.setApproximateDOB(new java.sql.Timestamp(1464425364343l));
        corecontacts.setFirstName("4HL7k0DQ68gxAd17Ov3VhdSfywpDebncV0Sqv5XO0U52rxWv0R");
        corecontacts.setLastName("ZWCu0L5wHbucdECuxtCX2NulCKheLdyw3kPiDtodmFWCheoTmY");
        corecontacts.setAge(13);
        corecontacts.setEmailId("FcmO1YtWOQb0LIko7ZPcj6ADze8iBgTY5qncuzpdNSSfk4Emxh");
        corecontacts.setNativeTitle("AUT7tUbdC2OkgpjzQuUn1UAbuosirQRpSOU9v3zFM7t7qbJ0F7");
        Gender gender = new Gender();
        gender.setGender("QpcHq3CeX0Rz8Q5Rd1nW7ZI8IgykO6ldj0uo7PpctXpHBE9VbL");
        Gender GenderTest = new Gender();
        if (isSave) {
            GenderTest = genderRepository.save(gender);
            map.put("GenderPrimaryKey", gender._getPrimarykey());
        }
        Title title = new Title();
        title.setTitles("W2R1fofWVMW5JJm4vRU6HuPBxR48IBPfTgQcp3lidbC9AwOWwU");
        Title TitleTest = new Title();
        if (isSave) {
            TitleTest = titleRepository.save(title);
            map.put("TitlePrimaryKey", title._getPrimarykey());
        }
        Language language = new Language();
        language.setAlpha3("NGJ");
        language.setLanguageIcon("n6vd4i7HnW0IAZTrkt5ojyMG8qzqBJn7A7LUXMXOwWh4fB4cWO");
        language.setLanguageDescription("AYTAHZx9bZZcLEddQmXmhByntPfe7YiZ3tZxa2lcs8RBMrsyMD");
        language.setAlpha2("ni");
        language.setAlpha4parentid(2);
        language.setLanguageType("iX97AGvdRRZQiTEYkdm9qpiqIiSCxBRB");
        language.setAlpha4("lI5z");
        language.setLanguage("fKydt29QTydw07mc7crnu99SgSUooy7akdxKvrDPAUVzwV8SOj");
        Language LanguageTest = new Language();
        if (isSave) {
            LanguageTest = languageRepository.save(language);
            map.put("LanguagePrimaryKey", language._getPrimarykey());
        }
        Timezone timezone = new Timezone();
        timezone.setCities("lgt9yG61WlW8hl1SdsAdFWiP2xLGIQ47JrW9t1tMKdMIFofoiV");
        timezone.setUtcdifference(10);
        timezone.setCountry("6GB5hXPjvckxkkriKyUpeLL9kBaTOesiMf4LcjVqdddvKPd2KK");
        timezone.setTimeZoneLabel("ScC8MXH5aq7hGX79CmnCxZlxrj7kBatJuEGTwWN8ULcpU568Wj");
        timezone.setGmtLabel("1Z0EsBlQEosPdLrycnVamrkOLyUvM6yOlonpEhg2eS30wYnqrd");
        corecontacts.setNativeLastName("mZjEwSOMsK36fM6zrd1SS1mSHL0nbdDbn5Rc3QKIzYmoXKsgSx");
        corecontacts.setNativeMiddleName("iJwGwSqu4Jxptc4boKUflCxte7dl5963sHydMI54wJZ3TBcfGy");
        corecontacts.setPhoneNumber("N6YwynMOjbis4Gle9hU2");
        corecontacts.setApproximateDOB(new java.sql.Timestamp(1464425364474l));
        corecontacts.setFirstName("1eM15DKaIVOFW2td9hkf3J7Vjh0aVuu974JMbahPSyWht4PhSS");
        corecontacts.setLastName("A0vkGsQqgBYRNiQmYHDTq4LOmnAhGUYzXJJ2O8GaPxAePpzWH9");
        corecontacts.setAge(66);
        corecontacts.setEmailId("bd76kVDFHtISi9hicRMUkUp595jddh5aSN148fX1qjsBueEejI");
        corecontacts.setNativeTitle("UqhkoDTQ2jDRG9fsbf30BZnfpJhbstw5pHK175cxk1gFuvgn1x");
        corecontacts.setGenderId((java.lang.String) GenderTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setMiddleName("2QPe1B6JgE7IeQ5bxJXb8tSeA9HtoIIim24jr4sFvZTwZWNUpE");
        corecontacts.setTitleId((java.lang.String) TitleTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setNativeLanguageCode((java.lang.String) LanguageTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setNativeFirstName("bMILvEpIqnxUzoxFzxe8TPsHlTxAGPoYgZ8PKt6yQ1OSWuJanI");
        timezone.setTimeZoneId(null);
        corecontacts.setTimezone(isSave ? timezoneRepository.save(timezone) : timezone);
        if (isSave) {
            map.put("TimezonePrimaryKey", timezone._getPrimarykey());
        }
        corecontacts.setDateofbirth(new java.sql.Timestamp(1464425365359l));
        java.util.List<CommunicationData> listOfCommunicationData = new java.util.ArrayList<CommunicationData>();
        CommunicationData communicationdata = new CommunicationData();
        CommunicationType communicationtype = new CommunicationType();
        communicationtype.setCommTypeName("y0oI2olzREWgeiOCQXBXEOFBpwLPKNXncUTVTvBgQqS4ZAnkcl");
        communicationtype.setCommTypeDescription("milXeFl6g9VGDn9VL4lcXCEspWVGGyqyfnNV2Y5NjVkFZASPbt");
        CommunicationGroup communicationgroup = new CommunicationGroup();
        communicationgroup.setCommGroupName("sGjwveQr8LlyZztw0dXjuV1gERN3V7qVJg0oEASxLM4hzDEOVP");
        communicationgroup.setCommGroupDescription("XZOwE0e5ub0YhFlXttiUiqMXE2tnfDM1Aq4fWceRYBZUpotDsz");
        CommunicationGroup CommunicationGroupTest = new CommunicationGroup();
        if (isSave) {
            CommunicationGroupTest = communicationgroupRepository.save(communicationgroup);
            map.put("CommunicationGroupPrimaryKey", communicationgroup._getPrimarykey());
        }
        communicationtype.setCommTypeName("rrn86fXBIIqe2rd64psPg84vHGfzEHXeDPhWCqCC3QaJ3AnhjH");
        communicationtype.setCommTypeDescription("b8jy56nP08N3Dqvaq9Rzjp8EjWSYZVtLFsGOCX6KwyM2vf7efd");
        communicationtype.setCommGroupId((java.lang.String) CommunicationGroupTest._getPrimarykey()); /* ******Adding refrenced table data */
        CommunicationType CommunicationTypeTest = new CommunicationType();
        if (isSave) {
            CommunicationTypeTest = communicationtypeRepository.save(communicationtype);
            map.put("CommunicationTypePrimaryKey", communicationtype._getPrimarykey());
        }
        communicationdata.setCommType((java.lang.String) CommunicationTypeTest._getPrimarykey()); /* ******Adding refrenced table data */
        communicationdata.setCommData("jeUioRhF9j");
        communicationdata.setCommGroupId((java.lang.String) CommunicationGroupTest._getPrimarykey()); /* ******Adding refrenced table data */
        listOfCommunicationData.add(communicationdata);
        corecontacts.addAllCommunicationData(listOfCommunicationData);
        java.util.List<Address> listOfAddress = new java.util.ArrayList<Address>();
        Address address = new Address();
        address.setAddress3("1hgBwNIaL18gpr2Zw5L6tgNMXlN0Dv87McnGWJLQ3sQBbcK9uU");
        address.setLongitude("GCjwrkqRLvYfrfPD7CQRreVvXrUZmtvyLoadvjPCTAOP3NTDOm");
        Country country = new Country();
        country.setCapital("UfBWsX23DOKlMHjWmJrW4P9ei3bKSw8f");
        country.setCountryName("qY5wBZijMvpd8pWsxvJyAElCw7eBeWm0slYSAJbtYoEx2LtdXm");
        country.setCountryFlag("P6JYKUEDAg77POvUgPDki1cB9YQpudHVfhlRfEFYCUiJNvImFt");
        country.setCapitalLongitude(3);
        country.setIsoNumeric(677);
        country.setCurrencySymbol("bYfkQ6bW0roaMw1KUjUz8WUCOZRrNRe8");
        country.setCountryCode2("mtj");
        country.setCurrencyName("2WnJ4dK7ErJ6wE9hX1mpn5ppglMJhca3AKIE73anbdb90D3KoH");
        country.setCapitalLatitude(4);
        country.setCountryCode1("Lup");
        country.setCurrencyCode("Sn3");
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
            map.put("CountryPrimaryKey", country._getPrimarykey());
        }
        City city = new City();
        city.setCityFlag("RanBkkZUbNjSrQfpXNftpt2Xwte2uRWEsdBRNpXbt2I9ar83fd");
        city.setCityDescription("nuTVoLA7VjoHHPhYhDwFUbJgoops1RmIZYva0arBoeeUZf0yTp");
        city.setCityLatitude(2);
        city.setCityCode(1);
        State state = new State();
        state.setStateDescription("wzZKYFQkP7bbC6h3zXbMHZ2Kpi5XAsvtbGUG5BnekBu2tHtTmz");
        state.setStateName("HLaeclUpml5tfah64Lxkd33OzEJNrxl5RQbkYtYZBufrqUcLqF");
        state.setStateDescription("cyRwrcXPcgnivASEjD0ciHjxbyjHAk8xdl5HE64u72wiFXspST");
        state.setStateName("oD3Zzin52VaMovy5xgYzrd2i6EPYebQlZId2oHPyfn6C91kHJU");
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        state.setStateFlag("t7K0N8q9d1tVqjiO157oA7S91waIjKRTd7SC9uvynDgE3traJm");
        state.setStateCapitalLongitude(11);
        state.setStateCodeChar3("y94nw26nkElBTtE4e6N3bWHAQ0VdugJo");
        state.setStateCapital("MxEW9WHyJtR2rRp3ToP5iBlkM0OECN0Y3aYCMEzj4xQhBOtYuf");
        state.setStateCapitalLatitude(9);
        state.setStateCode(2);
        state.setStateCodeChar2("HcY6EhZAPIE5RDGM4FnFgFDCu25W4nMP");
        State StateTest = new State();
        if (isSave) {
            StateTest = stateRepository.save(state);
            map.put("StatePrimaryKey", state._getPrimarykey());
        }
        city.setCityFlag("rKEg3LyzdZ0FuYVmZPIDJRurAM1Hz2uN3stpH4RlwqGu04CooU");
        city.setCityDescription("oB9W8e0XPMzcFm0MVRjI8i0SRbiT2LHeIysv9xN6K2VVjS1C6E");
        city.setCityLatitude(1);
        city.setCityCode(1);
        city.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityCodeChar2("4zPc3Z4RuXkgh1PZJIQz4NHs1DMbuYQm");
        city.setCityLongitude(5);
        city.setCityName("acvyMwOffiz0KPd7QPpzGVcpEBt6nQJepN81RyOdrlc52eWoHY");
        city.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        City CityTest = new City();
        if (isSave) {
            CityTest = cityRepository.save(city);
            map.put("CityPrimaryKey", city._getPrimarykey());
        }
        AddressType addresstype = new AddressType();
        addresstype.setAddressTypeIcon("tWzwRKlAMzV7qAsDQDXhX8HLxIS2S54MaLWNXhfwAeUTRecUzr");
        addresstype.setAddressTypeDesc("LZA0TcHHba8YOXpBbGTQUSVTTNFQl6KGGQY1dM4X2ZwzJb8AO0");
        addresstype.setAddressType("8XCCY3aQkcpBANC8juB1133JO8FldH5gPzinjsMbHaDSXLBR8Q");
        AddressType AddressTypeTest = new AddressType();
        if (isSave) {
            AddressTypeTest = addresstypeRepository.save(addresstype);
            map.put("AddressTypePrimaryKey", addresstype._getPrimarykey());
        }
        address.setAddress3("0V0uthF1avDiktKQC16DjsjAEjIDXlaxY8tx3ByHaPpEPF4EZY");
        address.setLongitude("KTy9LQUcon6B16uKkNcfbaC7Wuhzqltevz6nXVmsRz9fUOsJZ0");
        address.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setAddress1("mH6IMYwzasmikpZvBd5I6ZLaQth453a8nGVPnYHTENbvaiLRoV");
        address.setCityId((java.lang.String) CityTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setAddressTypeId((java.lang.String) AddressTypeTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setAddress2("yh0UUr8llZbmgapcpPFfcXmJY5CbUTF5PVwN7ySt0jWBVT70jS");
        address.setAddressLabel("U3eswpvUDWQ");
        address.setLatitude("ciK43JsPuH2OGfmfOAlqx25N4BDHQi2rncLLg1kIcHWZI6RQRr");
        address.setZipcode("99M1zG");
        address.setStateId((java.lang.String) StateTest._getPrimarykey());
        listOfAddress.add(address);
        corecontacts.addAllAddress(listOfAddress);
        Login login = new Login();
        user.setUserId(null);
        login.setUser(user);
        corecontacts.setContactId(null);
        login.setCoreContacts(corecontacts);
        login.setServerAuthText("aJc73IgUIJxL8aQS");
        login.setServerAuthImage("nu7u1MtftnZErZqhJlDlL5LR5Jk7Tarx");
        login.setIsAuthenticated(true);
        login.setFailedLoginAttempts(11);
        login.setLoginId("1RR3DNSrVWmTIYK4SdpXTet0Jjb3Gt4i3puA197Q4LsnX2VAUT");
        login.setEntityValidator(entityValidator);
        return login;
    }

    @Test
    public void test1Save() {
        try {
            Login login = createLogin(true);
            login.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            login.isValid();
            loginRepository.save(login);
            map.put("LoginPrimaryKey", login._getPrimarykey());
            map.put("UserPrimaryKey", login.getUser()._getPrimarykey());
            map.put("CoreContactsPrimaryKey", login.getCoreContacts()._getPrimarykey());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private UserRepository<User> userRepository;

    @Autowired
    private UserAccessLevelRepository<UserAccessLevel> useraccesslevelRepository;

    @Autowired
    private UserAccessDomainRepository<UserAccessDomain> useraccessdomainRepository;

    @Autowired
    private QuestionRepository<Question> questionRepository;

    @Autowired
    private CoreContactsRepository<CoreContacts> corecontactsRepository;

    @Autowired
    private GenderRepository<Gender> genderRepository;

    @Autowired
    private TitleRepository<Title> titleRepository;

    @Autowired
    private LanguageRepository<Language> languageRepository;

    @Autowired
    private TimezoneRepository<Timezone> timezoneRepository;

    @Autowired
    private CommunicationTypeRepository<CommunicationType> communicationtypeRepository;

    @Autowired
    private CommunicationGroupRepository<CommunicationGroup> communicationgroupRepository;

    @Autowired
    private AddressRepository<Address> addressRepository;

    @Autowired
    private CountryRepository<Country> countryRepository;

    @Autowired
    private CityRepository<City> cityRepository;

    @Autowired
    private StateRepository<State> stateRepository;

    @Autowired
    private AddressTypeRepository<AddressType> addresstypeRepository;

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("LoginPrimaryKey"));
            Login login = loginRepository.findById((java.lang.String) map.get("LoginPrimaryKey"));
            login.setServerAuthText("c3TfBXnktL5jTxId");
            login.setServerAuthImage("PI7kBXziw9NJtCnaPbpC5OKnIxCeNB3f");
            login.setFailedLoginAttempts(8);
            login.setLoginId("O6X28CI8Y3zn4KMB8pc1dN0bUWXPb6Iyj2SZF7lbnroDtwu0z3");
            login.setVersionId(1);
            login.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            loginRepository.update(login);
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("LoginPrimaryKey"));
            loginRepository.findById((java.lang.String) map.get("LoginPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void testNQFindUnMappedUser() {
        try {
            loginRepository.FindUnMappedUser();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testNQFindMappedUser() {
        try {
            loginRepository.FindMappedUser();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("LoginPrimaryKey"));
            loginRepository.delete((java.lang.String) map.get("LoginPrimaryKey")); /* Deleting refrenced data */
            addresstypeRepository.delete((java.lang.String) map.get("AddressTypePrimaryKey")); /* Deleting refrenced data */
            cityRepository.delete((java.lang.String) map.get("CityPrimaryKey")); /* Deleting refrenced data */
            stateRepository.delete((java.lang.String) map.get("StatePrimaryKey")); /* Deleting refrenced data */
            countryRepository.delete((java.lang.String) map.get("CountryPrimaryKey")); /* Deleting refrenced data */
            communicationtypeRepository.delete((java.lang.String) map.get("CommunicationTypePrimaryKey")); /* Deleting refrenced data */
            communicationgroupRepository.delete((java.lang.String) map.get("CommunicationGroupPrimaryKey")); /* Deleting refrenced data */
            timezoneRepository.delete((java.lang.String) map.get("TimezonePrimaryKey")); /* Deleting refrenced data */
            languageRepository.delete((java.lang.String) map.get("LanguagePrimaryKey")); /* Deleting refrenced data */
            titleRepository.delete((java.lang.String) map.get("TitlePrimaryKey")); /* Deleting refrenced data */
            genderRepository.delete((java.lang.String) map.get("GenderPrimaryKey")); /* Deleting refrenced data */
            questionRepository.delete((java.lang.String) map.get("QuestionPrimaryKey")); /* Deleting refrenced data */
            useraccessdomainRepository.delete((java.lang.String) map.get("UserAccessDomainPrimaryKey")); /* Deleting refrenced data */
            useraccesslevelRepository.delete((java.lang.String) map.get("UserAccessLevelPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateLogin(EntityTestCriteria contraints, Login login) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            login.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            login.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            login.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            loginRepository.save(login);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "loginId", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "loginId", "GKhrW1En9aaTwJ8ikJ8O7iPXAdMzN1PEvZTRRg42OMjFstFrqgCBlJDix4qQiR5Ueg8cPS6f6rE4Su9NiALOOkaTXq0k0cZRzQ2eBo2JUcgDX6olmMz0xKZGQADHrWAYW7gVukkxSjgOJ4sIWjfcqBpc2yRvI8CwYS9683AFM3FNLrBnKTtfDZ2BhjzVm8Qx9rQbmb49j"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 3, "serverAuthImage", "BaRg4lVpPhlboRFAWHcjlMx9fKtDHrlXJ"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "serverAuthText", "TL1QjrNrMgdwmfr5h"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 5, "failedLoginAttempts", 12));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "isAuthenticated", true));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                Login login = createLogin(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = login.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(login, null);
                        validateLogin(contraints, login);
                        failureCount++;
                        break;
                    case 2:
                        login.setLoginId(contraints.getNegativeValue().toString());
                        validateLogin(contraints, login);
                        failureCount++;
                        break;
                    case 3:
                        login.setServerAuthImage(contraints.getNegativeValue().toString());
                        validateLogin(contraints, login);
                        failureCount++;
                        break;
                    case 4:
                        login.setServerAuthText(contraints.getNegativeValue().toString());
                        validateLogin(contraints, login);
                        failureCount++;
                        break;
                    case 5:
                        login.setFailedLoginAttempts(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateLogin(contraints, login);
                        failureCount++;
                        break;
                    case 6:
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (failureCount > 0) {
            org.junit.Assert.fail();
        }
    }
}
