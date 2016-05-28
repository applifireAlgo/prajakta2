package com.app.server.service.organization.locationmanagement;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import com.app.config.WebConfigExtended;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.organization.locationmanagement.AddressRepository;
import com.app.shared.organization.locationmanagement.Address;
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
public class AddressTestCase extends EntityTestCriteria {

    @Autowired
    private AddressRepository<Address> addressRepository;

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

    private Address createAddress(Boolean isSave) throws Exception {
        Country country = new Country();
        country.setCapital("C0oNfnfPBGxqNKKDFBSl9BoMXvO52otf");
        country.setCountryName("Z3AjjaFFTMXy3rPdFfNpBWdcRC4KtsK3kcdmRoY0uvuX1jJd0u");
        country.setCountryFlag("lAdlUgkeKhPpAuxpP1WhwYMCwm9Uihjg89hnpZmnYKPj9mPsUm");
        country.setCapitalLongitude(6);
        country.setIsoNumeric(477);
        country.setCurrencySymbol("zT3ZXATMyQ8k6QWi40XTRE4lhu2dY2B2");
        country.setCountryCode2("tdm");
        country.setCurrencyName("et9AZrx0J9Z0HajoMXUN6qHdE2HhW2symAOXuXMMcmppBO6Oza");
        country.setCapitalLatitude(9);
        country.setCountryCode1("5a2");
        country.setCurrencyCode("o2y");
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
            map.put("CountryPrimaryKey", country._getPrimarykey());
        }
        City city = new City();
        city.setCityFlag("LxDmv1ZDe1Rg6M3M0ILydbabjTzppaNj3KOGz4uZ7rBj64aEPN");
        city.setCityDescription("m15og6vk3bHydw6O7IDMXNDpclSkFdBm8yB0KDDap3D9XVpCNj");
        city.setCityLatitude(3);
        city.setCityCode(3);
        State state = new State();
        state.setStateDescription("mMmvmQjAceBY3wcqiS1gxXd7rM0qzKRefoaxzRcZkUI1uIZRoD");
        state.setStateName("xxm0MheKcq92pnkJF1NpI13QSeHf8Ur9TkeIAgsNJusPo0xYLw");
        state.setStateDescription("u9Y1BYqwifVPlwp7K1lhv03qWtZnrObPN8Bp2MlcnCm1QjlTC2");
        state.setStateName("UFumJXPCiyxR7z4ru7FQaMMymZDSpxeYHiUFsRanBfcITjtVFZ");
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        state.setStateFlag("u1vMblLx6YjckpKfAjJL190u5Z1FaUIYyexRfuRL8CHJANQ9LG");
        state.setStateCapitalLongitude(7);
        state.setStateCodeChar3("WgHejB5s8E3dMepNxVYjVQY6Z1jvfK11");
        state.setStateCapital("CnuEroGElCbBTObK7puiRDdsYJDoJiJREpiqiGRit8edsuQtUl");
        state.setStateCapitalLatitude(5);
        state.setStateCode(2);
        state.setStateCodeChar2("Ru9ugts2C8b7LzEhXSQ8rR04AFJWhiD0");
        State StateTest = new State();
        if (isSave) {
            StateTest = stateRepository.save(state);
            map.put("StatePrimaryKey", state._getPrimarykey());
        }
        city.setCityFlag("lcR9sXEpvHARF7w2ZtWEPW1ioD9oQGVr7bfGqYu1hrQSEQfuiK");
        city.setCityDescription("hc7TbRQkupyUnXna5KInZyPFiQKEHOTnPTpC4UB5je0cc3jKbw");
        city.setCityLatitude(2);
        city.setCityCode(3);
        city.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityCodeChar2("FHPbziv1yHvF7XfkKyGalhvPZvMNE7sc");
        city.setCityLongitude(1);
        city.setCityName("flcUBxYFxPBGrXM4fnKnKkVeODJDKj2CFif0qvs1M3u2HjjvDy");
        city.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        City CityTest = new City();
        if (isSave) {
            CityTest = cityRepository.save(city);
            map.put("CityPrimaryKey", city._getPrimarykey());
        }
        AddressType addresstype = new AddressType();
        addresstype.setAddressTypeIcon("I8wHOUXpWrkfAFlyUzMQIOLmy42Xps4PhbcjzJzO25d1eMr5LO");
        addresstype.setAddressTypeDesc("TVIhQ8D9YHjV2KBZ1aRRNkY5TxpidMEZFJzaFNH1sadS6xKyE5");
        addresstype.setAddressType("oFyM2Hh2aUVD55j2aYmbXNbAmggVVwe31SHIQwUx1LOySWtffj");
        AddressType AddressTypeTest = new AddressType();
        if (isSave) {
            AddressTypeTest = addresstypeRepository.save(addresstype);
            map.put("AddressTypePrimaryKey", addresstype._getPrimarykey());
        }
        Address address = new Address();
        address.setAddress3("uCqyqQ8K5nnEyTdvElrRm7yDiTq32jZSBWGxvgdplsXgCdM76S");
        address.setLongitude("6ZDl6Qg0mS5LOVBGzRgyUZGrrkcwSSONaZC9vUOhJYI9veDcXx");
        address.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setAddress1("VzyEvbqHbkOxtr183RBRfNgn2g7NnasCqlIeRGYx5LkZNeV8a0");
        address.setCityId((java.lang.String) CityTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setAddressTypeId((java.lang.String) AddressTypeTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setAddress2("ylB4Fej17F6dGLJ2YAusPh58I5cQh2BdQyXmbHGEht7hwAONM5");
        address.setAddressLabel("c2xIrOTBKNv");
        address.setLatitude("pVFugOOMNECI9jphy1GPDdNxmdEr3uBGR6Pj6aShPmQUeW1IXJ");
        address.setZipcode("cRFHL8");
        address.setStateId((java.lang.String) StateTest._getPrimarykey());
        address.setEntityValidator(entityValidator);
        return address;
    }

    @Test
    public void test1Save() {
        try {
            Address address = createAddress(true);
            address.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            address.isValid();
            addressRepository.save(address);
            map.put("AddressPrimaryKey", address._getPrimarykey());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

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
            org.junit.Assert.assertNotNull(map.get("AddressPrimaryKey"));
            Address address = addressRepository.findById((java.lang.String) map.get("AddressPrimaryKey"));
            address.setAddress3("SQxgZ6AMBE3wod0TSn4mlalCJQe8qDnC3j45kCm6GByf6eS3sE");
            address.setLongitude("466IBq59IHnFQObB8gazzfseddt70o3RZply2y1lJwzRkTOxlH");
            address.setAddress1("zl9mVpBRPKwUKGyAC4OlicbqTaXrvhYyeOm6wQdz5Kms0UkQ0Y");
            address.setVersionId(1);
            address.setAddress2("biqFBc32njLLSVtPaRY1KDYVmVhiO5EShEwFThxFGvNWmdV6WI");
            address.setAddressLabel("cDTHf3kpfle");
            address.setLatitude("CWx0EPNugB1CnblU8Tdy9SVw3jj8DPZ2NuQNG1cNFr898DLXco");
            address.setZipcode("4F200F");
            address.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            addressRepository.update(address);
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBycountryId() {
        try {
            java.util.List<Address> listofcountryId = addressRepository.findByCountryId((java.lang.String) map.get("CountryPrimaryKey"));
            if (listofcountryId.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBycityId() {
        try {
            java.util.List<Address> listofcityId = addressRepository.findByCityId((java.lang.String) map.get("CityPrimaryKey"));
            if (listofcityId.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findByaddressTypeId() {
        try {
            java.util.List<Address> listofaddressTypeId = addressRepository.findByAddressTypeId((java.lang.String) map.get("AddressTypePrimaryKey"));
            if (listofaddressTypeId.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBystateId() {
        try {
            java.util.List<Address> listofstateId = addressRepository.findByStateId((java.lang.String) map.get("StatePrimaryKey"));
            if (listofstateId.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("AddressPrimaryKey"));
            addressRepository.findById((java.lang.String) map.get("AddressPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("AddressPrimaryKey"));
            addressRepository.delete((java.lang.String) map.get("AddressPrimaryKey")); /* Deleting refrenced data */
            addresstypeRepository.delete((java.lang.String) map.get("AddressTypePrimaryKey")); /* Deleting refrenced data */
            cityRepository.delete((java.lang.String) map.get("CityPrimaryKey")); /* Deleting refrenced data */
            stateRepository.delete((java.lang.String) map.get("StatePrimaryKey")); /* Deleting refrenced data */
            countryRepository.delete((java.lang.String) map.get("CountryPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateAddress(EntityTestCriteria contraints, Address address) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            address.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            address.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            address.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            addressRepository.save(address);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 1, "addressLabel", "Ejf8NQF5GXrY"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "address1", "tTcfvTFgl4pDtDm19p6pKmJSow44RgkE8HdcMbFyMHTdLtHvLse6PRexY"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 3, "address2", "GW8k4z2cDIc8yChx4zIn6fHoddQ5RMjzb2ZMA2SgWoY6wAiMDUXJuh2km"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "address3", "jUtGNT0l1S5FgZxzgHsHBnsmcJZn9nHYfSxtHxzOUuFvcgDrDL3CnMI53"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 5, "zipcode", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "zipcode", "uB9sozO"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 7, "latitude", "Yv3jCoWz526XanTERMwthEUkpbL4EPzMvW7TPicNVscRq2UkZjEQGRY8HavaNIf1c"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 8, "longitude", "0zAZck6X8qfU3NXHhgdpuq8y4PYNBokROYXmNYDLbsbisrLGDA28FBoxqOokCRdyV"));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                Address address = createAddress(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = address.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        address.setAddressLabel(contraints.getNegativeValue().toString());
                        validateAddress(contraints, address);
                        failureCount++;
                        break;
                    case 2:
                        address.setAddress1(contraints.getNegativeValue().toString());
                        validateAddress(contraints, address);
                        failureCount++;
                        break;
                    case 3:
                        address.setAddress2(contraints.getNegativeValue().toString());
                        validateAddress(contraints, address);
                        failureCount++;
                        break;
                    case 4:
                        address.setAddress3(contraints.getNegativeValue().toString());
                        validateAddress(contraints, address);
                        failureCount++;
                        break;
                    case 5:
                        field.setAccessible(true);
                        field.set(address, null);
                        validateAddress(contraints, address);
                        failureCount++;
                        break;
                    case 6:
                        address.setZipcode(contraints.getNegativeValue().toString());
                        validateAddress(contraints, address);
                        failureCount++;
                        break;
                    case 7:
                        address.setLatitude(contraints.getNegativeValue().toString());
                        validateAddress(contraints, address);
                        failureCount++;
                        break;
                    case 8:
                        address.setLongitude(contraints.getNegativeValue().toString());
                        validateAddress(contraints, address);
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
