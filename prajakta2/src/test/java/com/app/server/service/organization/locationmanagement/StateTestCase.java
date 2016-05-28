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
import com.app.server.repository.organization.locationmanagement.StateRepository;
import com.app.shared.organization.locationmanagement.State;
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

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = WebConfigExtended.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class StateTestCase extends EntityTestCriteria {

    @Autowired
    private StateRepository<State> stateRepository;

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

    private State createState(Boolean isSave) throws Exception {
        Country country = new Country();
        country.setCapital("rUsYuA8OYnQlBiP3FELRt3RPtJvIgv59");
        country.setCountryName("b8TJqtxIOsLelYjLFo1WoikyyMtnZCxhzMXV7FPUeYZFcdMo32");
        country.setCountryFlag("cKkoPcXqngwjjFLsa5USeZ5ymqUsmb37EgYl9519VhrtfMTaeZ");
        country.setCapitalLongitude(1);
        country.setIsoNumeric(901);
        country.setCurrencySymbol("Xd0wzaJFmgWSZIxVM8LQ5vvJCa1jkNwN");
        country.setCountryCode2("1vN");
        country.setCurrencyName("rmkRNpFSHiLccjrbCDUwN5q9CexIoVi1V6kkqZe0PrzgTWXNpc");
        country.setCapitalLatitude(6);
        country.setCountryCode1("yuc");
        country.setCurrencyCode("7OG");
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
            map.put("CountryPrimaryKey", country._getPrimarykey());
        }
        State state = new State();
        state.setStateDescription("hYUdLRUamhfMvL26CZS3zLC6DwDLu9iccF6K4zrjp9uLEBr64p");
        state.setStateName("E4JNmuEES8YRjLHq98u1n2B3GITjm7POTJG9ob39yoXBcJSmR8");
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey());
        state.setStateFlag("nWVt8XaTLDb8SRqIgem0E9xaUFWoHCWlZmUdFLoT3GG4KBkCKd");
        state.setStateCapitalLongitude(5);
        state.setStateCodeChar3("HNRcED89AytN4KcGT2yLAqCGj5Kr5GxR");
        state.setStateCapital("9X19Z51ogQV7nfuzn3Rn1xRQSv0aPbIPwS6agYMrCpQcQwzLAU");
        state.setStateCapitalLatitude(3);
        state.setStateCode(1);
        state.setStateCodeChar2("wFayB1dYjaXcWyfN65IIeossqeTbuIdC");
        state.setEntityValidator(entityValidator);
        return state;
    }

    @Test
    public void test1Save() {
        try {
            State state = createState(true);
            state.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            state.isValid();
            stateRepository.save(state);
            map.put("StatePrimaryKey", state._getPrimarykey());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private CountryRepository<Country> countryRepository;

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("StatePrimaryKey"));
            State state = stateRepository.findById((java.lang.String) map.get("StatePrimaryKey"));
            state.setStateDescription("pZf80Qq2VqcnGWt2Qmu6p89jzPsKsF66rNdOY1iIoAPAQe2bmt");
            state.setStateName("NhIU7ItkoKwz5herdE07ZjG49xdUkyoYgdq1YyogvCYzIOeqGA");
            state.setStateFlag("8gPbAckkUtmSN8jAI68DP0NsRARmyTJpCEqI8IN9LttCPQ7Gxx");
            state.setStateCapitalLongitude(6);
            state.setStateCodeChar3("xLPHWikmpD40z4oIDVE0hlv33k685R9l");
            state.setStateCapital("aPzDBizkuTyANp6Cs8Vx7Ol4nLpNS3CgN6tmky9C2wfkHY0iK9");
            state.setStateCapitalLatitude(4);
            state.setStateCode(2);
            state.setStateCodeChar2("rTyD3JDZMNXxXN8u1SsCNP3cbsEoCHwF");
            state.setVersionId(1);
            state.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            stateRepository.update(state);
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBycountryId() {
        try {
            java.util.List<State> listofcountryId = stateRepository.findByCountryId((java.lang.String) map.get("CountryPrimaryKey"));
            if (listofcountryId.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("StatePrimaryKey"));
            stateRepository.findById((java.lang.String) map.get("StatePrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("StatePrimaryKey"));
            stateRepository.delete((java.lang.String) map.get("StatePrimaryKey")); /* Deleting refrenced data */
            countryRepository.delete((java.lang.String) map.get("CountryPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateState(EntityTestCriteria contraints, State state) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            state.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            state.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            state.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            stateRepository.save(state);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "stateName", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "stateName", "M1hZQRQUvKCvbRL3hesX78nrsBlEiqBN5BiwP5AHIF4cNDC4iMdLm0pdHnNYIse9uLYlSkwLy4Rn6ZurzRVPjZzreO9Pi6VvJwU1yVjkPudIQEUIhPhwdEr5t0HmUJdu56jucxpS3FEgftIh8VBAs2RHphcFa6WDgmgKnD2ehXK2ox5YQpfeKKbqO5NC2bMMmZxQRwxsdekcQxA9XrtDtpo2MuVeonL7VjlhkScMrgiuAgtWHJpZIIKJ2G9qhY2jm"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 3, "stateCode", 3));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 4, "stateCodeChar2", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 5, "stateCodeChar2", "8ZVaqG1Ql1DT4pFb5KUF145MzDoLblSIA"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "stateCodeChar3", "OwYGRme1iwhfvekVgxSGnqMTqXEMHQUfF"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 7, "stateDescription", "FgFHbXfRzXpHp6tkQS9s2YwJstwDue1HN85CcuHJKigBsXHnKmyYgD27d5tIzTwGK59N8r0IU8hZETXqqPiZu1sloE2tHqYTV2X3lEI20Gxh9aV0a6mOq2E5EX1AYRPpl2biNjFX7r7S75jBblNzXzRqYrzEuAIuqMUS5Xe5D78kZk1gZQfwRWWZGrHTEXIvzG0xEdRvivbNUZjG1k12iBRpRyqhDyxKGnJDII9JXukLS1sGquZb9FLZAsdv7oss7"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 8, "stateFlag", "NdtnUjIx8CRFsgnq1I0xAX3rTDhmc95OnOpCSxtCmU93vcHbs6o4uHySgty3qtxd4zXj3h3Qj91X295MBQSQ2fc8P7lPrkXACyJBNZlNnPN29HI2NELgU5l4geJAiRN68"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 9, "stateCapital", "1FlIRBLvuHsazpjr2NvM5bmhozgW4k41BkszLw28h0hTANmTX71yL4RxUPLJvGCo7CeXHBROIx49U2gmQLWNJHjnDqSBkwsZMh1VHBt89W1z3ZbTW3Rz5ewWEUwkPLCfc"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 10, "stateCapitalLatitude", 16));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 11, "stateCapitalLongitude", 17));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                State state = createState(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = state.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(state, null);
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 2:
                        state.setStateName(contraints.getNegativeValue().toString());
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 3:
                        state.setStateCode(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 4:
                        field.setAccessible(true);
                        field.set(state, null);
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 5:
                        state.setStateCodeChar2(contraints.getNegativeValue().toString());
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 6:
                        state.setStateCodeChar3(contraints.getNegativeValue().toString());
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 7:
                        state.setStateDescription(contraints.getNegativeValue().toString());
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 8:
                        state.setStateFlag(contraints.getNegativeValue().toString());
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 9:
                        state.setStateCapital(contraints.getNegativeValue().toString());
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 10:
                        state.setStateCapitalLatitude(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 11:
                        state.setStateCapitalLongitude(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateState(contraints, state);
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
