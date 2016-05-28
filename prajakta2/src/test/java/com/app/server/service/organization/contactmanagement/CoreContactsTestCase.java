package com.app.server.service.organization.contactmanagement;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import com.app.config.WebConfigExtended;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.organization.contactmanagement.CoreContactsRepository;
import com.app.shared.organization.contactmanagement.CoreContacts;
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
public class CoreContactsTestCase extends EntityTestCriteria {

    @Autowired
    private CoreContactsRepository<CoreContacts> corecontactsRepository;

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

    private CoreContacts createCoreContacts(Boolean isSave) throws Exception {
        Gender gender = new Gender();
        gender.setGender("1EyxxsLflftSEPUVUrdN3djqbHd1PtmvMCaEwF8mmJmstnnoYV");
        Gender GenderTest = new Gender();
        if (isSave) {
            GenderTest = genderRepository.save(gender);
            map.put("GenderPrimaryKey", gender._getPrimarykey());
        }
        Title title = new Title();
        title.setTitles("YScpHUk8Rho09ckTRiihBvapSu9Vlfrrm6jniSvHPugoqE6Jqj");
        Title TitleTest = new Title();
        if (isSave) {
            TitleTest = titleRepository.save(title);
            map.put("TitlePrimaryKey", title._getPrimarykey());
        }
        Language language = new Language();
        language.setAlpha3("FMf");
        language.setLanguageIcon("266vjLiuZrLQeURrge4Wqglxy7VZLo5xmHdS9nnbix82SCr48H");
        language.setLanguageDescription("z7tAX2ZNGvBBxcaWjGvulXGM1Z1sUHZlYcAq9lR57QANymMKCO");
        language.setAlpha2("fk");
        language.setAlpha4parentid(3);
        language.setLanguageType("0Lxd14m20Ux6I8W6EM24YDRwYhBUKgVp");
        language.setAlpha4("2X9k");
        language.setLanguage("x14NbnBbaUuegamFm7wke1RSqxeiCzimH3HK2w5r3PsxDe79vB");
        Language LanguageTest = new Language();
        if (isSave) {
            LanguageTest = languageRepository.save(language);
            map.put("LanguagePrimaryKey", language._getPrimarykey());
        }
        Timezone timezone = new Timezone();
        timezone.setCities("PS1PqZZPnN4VYRijT22eeq8Dc8h6LGylvO9y366ON0Q6mzjmEz");
        timezone.setUtcdifference(10);
        timezone.setCountry("j6H2Bk9rcOK6pXbCxNniGlC8wBjezXvLPA18DhBGOULJ7DlF57");
        timezone.setTimeZoneLabel("qqowQY2fiOukuswVXtXy0RnwbfovlIRbkhQwODVbn1lso2xChS");
        timezone.setGmtLabel("5WCYUjLQqn8h534xu3CcJuhuoiLC5S70ajyhxYGXcsVD7gjfBG");
        CoreContacts corecontacts = new CoreContacts();
        corecontacts.setNativeLastName("1zg3ObE44XIatGOL258ugutSj56cNlzbYRS3XTUzue9dlIhKxq");
        corecontacts.setNativeMiddleName("e9sj4PeflycduHXvMm9oo6eqmtlNmYLgi7lVRAdVq8NbMP8vXS");
        corecontacts.setPhoneNumber("aPwysdWiERu1e0JmLR6c");
        corecontacts.setApproximateDOB(new java.sql.Timestamp(1464425341085l));
        corecontacts.setFirstName("nthjoxrckbvXf4GXGw6qDzx18IOLZUxZXVyMesSbM7MzHvsDkE");
        corecontacts.setLastName("XvCseWLu6tFpkw9f2vW10E8tfVEjRa3tVqubjg2n4dPIEBphog");
        corecontacts.setAge(93);
        corecontacts.setEmailId("QLBXk1vkfz3NA5fMqj60uuSHDaP83V6LbiBp2bYyXQkZzcEqAr");
        corecontacts.setNativeTitle("B8KnhnervEenMkGfFpInqFfN2eK8hKzgmUQhCGjkYzlVh3rTsP");
        corecontacts.setGenderId((java.lang.String) GenderTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setMiddleName("NkSnWWOnw93HYpYSCYnlzbC7BShs5jJoKvVR2PwLElnjCmpkhA");
        corecontacts.setTitleId((java.lang.String) TitleTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setNativeLanguageCode((java.lang.String) LanguageTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setNativeFirstName("2b2Qi9dOvNRrYw1dXFEqlGlZkzcRH9Lp182VgAjKLJtHdJWWll");
        timezone.setTimeZoneId(null);
        corecontacts.setTimezone(isSave ? timezoneRepository.save(timezone) : timezone);
        if (isSave) {
            map.put("TimezonePrimaryKey", timezone._getPrimarykey());
        }
        corecontacts.setDateofbirth(new java.sql.Timestamp(1464425342017l));
        java.util.List<CommunicationData> listOfCommunicationData = new java.util.ArrayList<CommunicationData>();
        CommunicationData communicationdata = new CommunicationData();
        CommunicationType communicationtype = new CommunicationType();
        communicationtype.setCommTypeName("5brMJ0UuGUi4yycdot17EI7uiTCnpwmkOCVMJuOr3qJnHIg6qu");
        communicationtype.setCommTypeDescription("wXrBsr3CqJPig7N5k3I1LZeTrfaqnGHI7aM6PdbsQLAEVyYmVp");
        CommunicationGroup communicationgroup = new CommunicationGroup();
        communicationgroup.setCommGroupName("ioHfEyhAPbEtEYqvh9Jb05wCVdMML3AzTBx0oSFs1MEgysy7F9");
        communicationgroup.setCommGroupDescription("6ODI0srQS8HkvVdpifKLd5zy5QfXeYLIai8BrUMkseKENnlqVJ");
        CommunicationGroup CommunicationGroupTest = new CommunicationGroup();
        if (isSave) {
            CommunicationGroupTest = communicationgroupRepository.save(communicationgroup);
            map.put("CommunicationGroupPrimaryKey", communicationgroup._getPrimarykey());
        }
        communicationtype.setCommTypeName("njQVevSV9BSIoEYDwxV5aB240tc89qkI6mkpkEpgGQOM3epEuh");
        communicationtype.setCommTypeDescription("Abx91ctaOPhOuznVOUmfefLQjgm0cV8IKpwhHEaRkL5vAE9X7d");
        communicationtype.setCommGroupId((java.lang.String) CommunicationGroupTest._getPrimarykey()); /* ******Adding refrenced table data */
        CommunicationType CommunicationTypeTest = new CommunicationType();
        if (isSave) {
            CommunicationTypeTest = communicationtypeRepository.save(communicationtype);
            map.put("CommunicationTypePrimaryKey", communicationtype._getPrimarykey());
        }
        communicationdata.setCommType((java.lang.String) CommunicationTypeTest._getPrimarykey()); /* ******Adding refrenced table data */
        communicationdata.setCommData("mW3Lr3MaaJ");
        communicationdata.setCommGroupId((java.lang.String) CommunicationGroupTest._getPrimarykey()); /* ******Adding refrenced table data */
        listOfCommunicationData.add(communicationdata);
        corecontacts.addAllCommunicationData(listOfCommunicationData);
        java.util.List<Address> listOfAddress = new java.util.ArrayList<Address>();
        Address address = new Address();
        address.setAddress3("DfLS9KklhUMQqHu0qC7ywz63oqtAJPSDCiRfPEF4WK9vfDlNRQ");
        address.setLongitude("NdhkGhk4spajuuIuqkQLXY8AUcK2vDSfES8IGTSjhtz2yCofyR");
        Country country = new Country();
        country.setCapital("isbBHF1bJOVFBwxgQTmo6vMc7t1NZ31r");
        country.setCountryName("INLw0haIcp4tKkS8U2KQ8AkJA9rKxORNRnQy8j3Tbi0zFxCEM3");
        country.setCountryFlag("aMPz9TJbTP2C0zEQ5afoKg79k3eHaiv7c9X6jLwWZOFlNbbPdK");
        country.setCapitalLongitude(5);
        country.setIsoNumeric(266);
        country.setCurrencySymbol("L3QhOdVtG3zRgckPQuayYEz7caTtDQlq");
        country.setCountryCode2("w0s");
        country.setCurrencyName("K8QnCDMNu6KJyngkRYFnBEaqKcHCuowSAOI46qkAgMyeNi8ntb");
        country.setCapitalLatitude(7);
        country.setCountryCode1("njs");
        country.setCurrencyCode("P2U");
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
            map.put("CountryPrimaryKey", country._getPrimarykey());
        }
        City city = new City();
        city.setCityFlag("1lfujZtqERqLShgbZxyWUWdjqjN3sA0hNYtEcdRfW5cCkyy6fP");
        city.setCityDescription("DUvn6BvZP8WubNneKDwL0Psj0DXFn5MSdbdhkwe7BAg8QpxSA7");
        city.setCityLatitude(1);
        city.setCityCode(1);
        State state = new State();
        state.setStateDescription("tcHJqkyRHoDUye9K3WGuEeywFjFy9vCKJbyd9MM9fRZYMbLX0y");
        state.setStateName("0GPGvxVOTlwfyiaAr2D4TY0jZ1EulTkNIHbc6Mefc3uqgIUWnb");
        state.setStateDescription("saeFGtpuDaFGB0gNI0xqlJRLCPas3NemWeb6KJZhKWsp429Vty");
        state.setStateName("HSSyOpIjUVNF2Xq7Gsf2ILtKDLd6K7R2ULZoDfGwcEcGDn6YIz");
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        state.setStateFlag("fi3Vp63eNDZWReY6wpYboPCCZlK61cvkKLr32xgazEtgWqneVX");
        state.setStateCapitalLongitude(8);
        state.setStateCodeChar3("fMZ6ofQJJdlvVqauUHICwgZYnkwtCnY4");
        state.setStateCapital("xnPAtarMeN2rsErTK9PTWvb38gIeW8FrlRfGb5lYYONcDDU5jD");
        state.setStateCapitalLatitude(7);
        state.setStateCode(1);
        state.setStateCodeChar2("ZcHWSYewhHArNszhbMs9wYtqaRFqHdUa");
        State StateTest = new State();
        if (isSave) {
            StateTest = stateRepository.save(state);
            map.put("StatePrimaryKey", state._getPrimarykey());
        }
        city.setCityFlag("LTir49joOWsVEB4r1RsHBv6prTDRv4eWKSwqUTh7eY9pQ9Dau5");
        city.setCityDescription("Jwhlkxvg16opSCBjcUao6QzQWoNHZoFnyezCg2ZldiCMIPjemv");
        city.setCityLatitude(5);
        city.setCityCode(1);
        city.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityCodeChar2("zAmfWXZ8QKehWttzp6DnYelfMrvogxib");
        city.setCityLongitude(8);
        city.setCityName("5wcvxC5ayBlqwvl3mfHydacrHRTUXFggm994DuZ4NykDYUnDC4");
        city.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        City CityTest = new City();
        if (isSave) {
            CityTest = cityRepository.save(city);
            map.put("CityPrimaryKey", city._getPrimarykey());
        }
        AddressType addresstype = new AddressType();
        addresstype.setAddressTypeIcon("G75UD4YG8JDdWjvJyMmwvOW3dG6ieEmHFWEy58OnBQKuOaUG06");
        addresstype.setAddressTypeDesc("0kd4uXjSfpXXmdF8AwW7D3KwjfkIaRtw8Cm0qZhLL5hYghFREJ");
        addresstype.setAddressType("WZKXo2BQBN5PKMKZyALnL26Ae4y7miAjBxWhqphF1jLtefuhBB");
        AddressType AddressTypeTest = new AddressType();
        if (isSave) {
            AddressTypeTest = addresstypeRepository.save(addresstype);
            map.put("AddressTypePrimaryKey", addresstype._getPrimarykey());
        }
        address.setAddress3("uMjFPrmf3qkCcnn1jWKT9e8SJ7qqiQzMvlo10fzqXNSpkKmAiq");
        address.setLongitude("RcoNBEVQAvWn4UfCcU7vLxviXeZ5dgUkI7HQuYpn3K1nIFWYmY");
        address.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setAddress1("hZc9xnC2VF713y6RZuXr0Qp8ehaHRStKAtaHgbFx9az0rlIlJG");
        address.setCityId((java.lang.String) CityTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setAddressTypeId((java.lang.String) AddressTypeTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setAddress2("0PuOE8clrNi2AYfNwQOBmGwnTlLUcMQ3bUTiGil9FiX2mo7e7X");
        address.setAddressLabel("XDmit3efocy");
        address.setLatitude("JJpBne3CDowPzkQkEdQJmMdK8I6uauS1RYL9tIyroCR6aovddn");
        address.setZipcode("mrh81c");
        address.setStateId((java.lang.String) StateTest._getPrimarykey());
        listOfAddress.add(address);
        corecontacts.addAllAddress(listOfAddress);
        corecontacts.setEntityValidator(entityValidator);
        return corecontacts;
    }

    @Test
    public void test1Save() {
        try {
            CoreContacts corecontacts = createCoreContacts(true);
            corecontacts.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            corecontacts.isValid();
            corecontactsRepository.save(corecontacts);
            map.put("CoreContactsPrimaryKey", corecontacts._getPrimarykey());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

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
            org.junit.Assert.assertNotNull(map.get("CoreContactsPrimaryKey"));
            CoreContacts corecontacts = corecontactsRepository.findById((java.lang.String) map.get("CoreContactsPrimaryKey"));
            corecontacts.setNativeLastName("Ufo7y1jjIOkMsk4VKsOVZGOJ3mqhl3ke0EDFygcuupOBKrniUC");
            corecontacts.setNativeMiddleName("AERDsOD2y7dGqYn2NC9ub0GrKCeh8Tg4BQNJIOv1lrcsbx4A0W");
            corecontacts.setPhoneNumber("9tP7kR9I9vbY3sJzAfvy");
            corecontacts.setApproximateDOB(new java.sql.Timestamp(1464425344617l));
            corecontacts.setVersionId(1);
            corecontacts.setFirstName("lstxKV09FNIk7Ug76ZXAzy31ROVZSeFgmVXGLI2MOvzur8pjgz");
            corecontacts.setLastName("AH6sk8AxR2pU65EediCbm85iDLoYc7O7E4zkHJxSUgzZgAKCxC");
            corecontacts.setAge(34);
            corecontacts.setEmailId("WfFLBQFrsgs1BbD0TAsauPcQww35UVr37JAc7cr6wZvZmpfVPt");
            corecontacts.setNativeTitle("Or02TSOxmSE1tMXYUT59GM9cGhGLoq3ol1ITdXsqgmNwsqX9hW");
            corecontacts.setMiddleName("jk4b7VnA4KJyhyAgNezU8fkVK7ARu84dy9jjjfTGQf3Es4UW8q");
            corecontacts.setNativeFirstName("JoZcNzTvIc8PTC4CYL1INxuBozrqgkePc8SzkUWPYCozRBfLnS");
            corecontacts.setDateofbirth(new java.sql.Timestamp(1464425345336l));
            corecontacts.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            corecontactsRepository.update(corecontacts);
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("CoreContactsPrimaryKey"));
            corecontactsRepository.findById((java.lang.String) map.get("CoreContactsPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBygenderId() {
        try {
            java.util.List<CoreContacts> listofgenderId = corecontactsRepository.findByGenderId((java.lang.String) map.get("GenderPrimaryKey"));
            if (listofgenderId.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBytitleId() {
        try {
            java.util.List<CoreContacts> listoftitleId = corecontactsRepository.findByTitleId((java.lang.String) map.get("TitlePrimaryKey"));
            if (listoftitleId.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBynativeLanguageCode() {
        try {
            java.util.List<CoreContacts> listofnativeLanguageCode = corecontactsRepository.findByNativeLanguageCode((java.lang.String) map.get("LanguagePrimaryKey"));
            if (listofnativeLanguageCode.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("CoreContactsPrimaryKey"));
            corecontactsRepository.delete((java.lang.String) map.get("CoreContactsPrimaryKey")); /* Deleting refrenced data */
            addresstypeRepository.delete((java.lang.String) map.get("AddressTypePrimaryKey")); /* Deleting refrenced data */
            cityRepository.delete((java.lang.String) map.get("CityPrimaryKey")); /* Deleting refrenced data */
            stateRepository.delete((java.lang.String) map.get("StatePrimaryKey")); /* Deleting refrenced data */
            countryRepository.delete((java.lang.String) map.get("CountryPrimaryKey")); /* Deleting refrenced data */
            communicationtypeRepository.delete((java.lang.String) map.get("CommunicationTypePrimaryKey")); /* Deleting refrenced data */
            communicationgroupRepository.delete((java.lang.String) map.get("CommunicationGroupPrimaryKey")); /* Deleting refrenced data */
            timezoneRepository.delete((java.lang.String) map.get("TimezonePrimaryKey")); /* Deleting refrenced data */
            languageRepository.delete((java.lang.String) map.get("LanguagePrimaryKey")); /* Deleting refrenced data */
            titleRepository.delete((java.lang.String) map.get("TitlePrimaryKey")); /* Deleting refrenced data */
            genderRepository.delete((java.lang.String) map.get("GenderPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateCoreContacts(EntityTestCriteria contraints, CoreContacts corecontacts) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            corecontacts.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            corecontacts.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            corecontacts.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            corecontactsRepository.save(corecontacts);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "firstName", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "firstName", "L14tpRxHYjRzgLH8ZYHoKfO8TfYOXOR00AveR0HLr9gvP9axUXTvHDFdB6xYEk5BxT59gti0YBCp2RuACupU8TZFwJaTmGPOxYf8TRv2DQKhQnsJaTq72WHTZyI59JrHkopV0xKU24zHbQU504aYI122FQdhOFpJ42cMh1bwi37aL0c2xXtjQ5SvmCe0MoDNDTyTEzcTmxyUjVYTaDMoQWm5dsI6SCHtRdaTHI33WyveV2YcVn1kw2VcJFsmd0vCY"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 3, "middleName", "Qha2SK9EbUVhXjbOXY9ehj3oa1IovM6PDk2hQ7yc1Z0uqoXsYRbdZGpINuiB89pMCF9vAQ863UisSVi6oYC3JKjsNmd4TcNMRBjhs9U65M7fT9nqAkYZ9NwbjebgWayeUlagSwnxGNMEVX01zlEPl8obbniQFkvmdk6fMxSE79rWhzcse7Kla4CJ8pyEZugnc7qfvgqFu9W1T46l8XeAwrZwHnx4eDYb5Dc62h3OkIzlrCi45E4BgIi8Y9DtuF62S"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 4, "lastName", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 5, "lastName", "L4VDX0OOV9l3VpIpcMkWUEhcEgdusXcZi7U0I7QjHy6ylW6TwPD03aY80DKsCmBe1f31hAH7XTKiR8AKYcvDsviM26t63FL9HIJ0krB2ExHBvkdlotIttRjb0DX8onVs9OUcYau4RXaco8uPfveWKjtuIryXj325ofLN7iLuzuRHQ91O6fagvCzS1iEiCinc5ppIJPReeEwwLniO2TvXg2qDSjQA3juACOFLQaWM3Kyg5iuY6KJMjwP0Sx01lCrrm"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "nativeTitle", "DYEMaCfwCMLBQeAtZGGQGaGiSpb8wmJbsHQ18UoCekPhtcGI40DlEh5dG3UQECVKH"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 7, "nativeFirstName", "UdZkLaDJcdqmUq4Pqr6QbeS2Yttro6DEP1wbov2dDfqDHIO17uzUNTb2D3XWtSlP44yfL82NbvV5cGx5ZKGdxRIyn9TNV095bfvYey0CGGWzRcy9ssdr2CyiqHN7cTyKgwfY0BtkDkFaL3VurYCzzC8AotcByYRoP1DAo2MJhZNb7mtiPcP25ZR4QNdIN01VRZu8iWMe8crGDmWtuDFZrUPiOxYLVpXbeQyc2sqJgdnYY2bsAdeZaovn6mQalHcNw"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 8, "nativeMiddleName", "ycNmUfYEkieuWKVFNB4nKk5jSk4QHtLWVkceiAJqZjGp8ykQV2JJSXGWfGNChzOaSw3AFScl00D5KVjM74t2CwZLEOjxltD0QGYHN05qADnSk5CDzP9CrQdE8aIsblXw896QgxhKKAZgSzKrL5eZzp0SHiygixuEeLRvq5oMofr08YoNiu00u05h4AEKt83SkpVsX5Qf2RPFHNMU1mcsWsaPcsSsfuuLsTNPrhd4QzCRrrn2o7z6tbQHuDTsSiJJd"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 9, "nativeLastName", "HH8Y8VwazCu4XvlYZenzXDMQqSX7d6cQxMfpKns7OCGtAZ38suayvws2L1mNx8eQAkb3SR6lxe5LSDhTOEowLib8hr9UvZiLy0txTnlYkbHrFUyL45oNFHGxuGCfHAzDK7dmcNfjuqt56lgEhteNshQF5TAHij9eQnLm0pKyHfMqZQDWX8nIYtJGoom6Rctg4FYvW7fWZFOn31qYjiyy8Mzx2VWjNaFgJSnjPg3H9RTyoDD3eMHwwalIBd7HnIr3V"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 10, "age", 171));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 11, "emailId", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 12, "emailId", "h9OAWszdOg2tyWaoIKr5krHpOGcLQyuDf05WbYNkJEYewGNk7iZbC5eYalFJZWV9xQPGH8XACQ12PqlGmUPh56OMglzyUWq6weAPWCrnqEXWZEX0gp9UpYAJlQsSB5ls4PFJXGblROgpyudCAtGndb9bJqppJZJJkFkfarp0t3t0ljfa1wth2f78eHtKe4p5PUGv9m0TMooqmwzo1NpfMStGGVAxjKsq3Xca051woNZ8HRXq7DUyJui32kzXOyE0Z"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 13, "phoneNumber", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 14, "phoneNumber", "jJQ3MhWc5YV8oEqCKnRKs"));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                CoreContacts corecontacts = createCoreContacts(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = corecontacts.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(corecontacts, null);
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 2:
                        corecontacts.setFirstName(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 3:
                        corecontacts.setMiddleName(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 4:
                        field.setAccessible(true);
                        field.set(corecontacts, null);
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 5:
                        corecontacts.setLastName(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 6:
                        corecontacts.setNativeTitle(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 7:
                        corecontacts.setNativeFirstName(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 8:
                        corecontacts.setNativeMiddleName(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 9:
                        corecontacts.setNativeLastName(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 10:
                        corecontacts.setAge(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 11:
                        field.setAccessible(true);
                        field.set(corecontacts, null);
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 12:
                        corecontacts.setEmailId(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 13:
                        field.setAccessible(true);
                        field.set(corecontacts, null);
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 14:
                        corecontacts.setPhoneNumber(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
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
