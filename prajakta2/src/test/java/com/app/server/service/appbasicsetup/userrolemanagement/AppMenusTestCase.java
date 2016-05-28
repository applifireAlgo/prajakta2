package com.app.server.service.appbasicsetup.userrolemanagement;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import com.app.config.WebConfigExtended;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.appbasicsetup.userrolemanagement.AppMenusRepository;
import com.app.shared.appbasicsetup.userrolemanagement.AppMenus;
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

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = WebConfigExtended.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class AppMenusTestCase extends EntityTestCriteria {

    @Autowired
    private AppMenusRepository<AppMenus> appmenusRepository;

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

    private AppMenus createAppMenus(Boolean isSave) throws Exception {
        AppMenus appmenus = new AppMenus();
        appmenus.setAutoSave(true);
        appmenus.setMenuDisplay(true);
        appmenus.setMenuIcon("mhe6vt4w5r6MrwhDYC1Fsnrmx7bRMfqTuJhkxEklvurYKTbFPy");
        appmenus.setUiType("VOF");
        appmenus.setMenuCommands("k9QUDuWSpxKbvz3DqXI4ujB9v6pASVAABrLUD0ovLedDYkIRqi");
        appmenus.setMenuTreeId("PFTeLutJXvUBKqR2fz9bmssWElSCELZhCfMmtDvrD0FnND5jSP");
        appmenus.setMenuAccessRights(11);
        appmenus.setRefObjectId("yNRv0ttgLjbzjl0Ir09PKcmODfGzg9nXg2Xkx0lA1BabpolEak");
        appmenus.setAppType(1);
        appmenus.setMenuLabel("wPPM5VRpudBOCdJ760AlVbT0uj01DszhbT00Mvtt7ci9XWYkFS");
        appmenus.setMenuHead(true);
        appmenus.setAppId("6pdRPZKyS58E71x67N2FvtcjkCURHEQOwFER39bvsnq49LRxQ4");
        appmenus.setMenuAction("t9omAitkLSsifTcOHMSYdXJIi7AvJgHTMJgquzuRdn2EaSXVii");
        appmenus.setEntityValidator(entityValidator);
        return appmenus;
    }

    @Test
    public void test1Save() {
        try {
            AppMenus appmenus = createAppMenus(true);
            appmenus.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            appmenus.isValid();
            appmenusRepository.save(appmenus);
            map.put("AppMenusPrimaryKey", appmenus._getPrimarykey());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("AppMenusPrimaryKey"));
            AppMenus appmenus = appmenusRepository.findById((java.lang.String) map.get("AppMenusPrimaryKey"));
            appmenus.setVersionId(1);
            appmenus.setMenuIcon("A3b803YGwDi24tffNWzRYQsklL4t0Xu99igaf5KdSH7jvHhjNW");
            appmenus.setUiType("HPJ");
            appmenus.setMenuCommands("vuGjy1MzXyyRd9wa74crKObXatjD9D3K3vUnbYYZNYIrDxccs4");
            appmenus.setMenuTreeId("B5lQajgzGjfmTyqdqv2KG71G9AQchRTGXkiHopPLoZO1RepJm9");
            appmenus.setMenuAccessRights(2);
            appmenus.setRefObjectId("HawoCkHw3j43rwZrPktwekLkY7pQnzP7AiOwUE4JqfVS8ZTjyr");
            appmenus.setAppType(1);
            appmenus.setMenuLabel("wwrJ2akEfTfLtcl5nWp1zfzvbYL5zl5WoUaoFXVFwuC1AawHuz");
            appmenus.setAppId("c7DACkgJoy6M3sPhFeMMxXt7PL3BeOip50mG5AWr9BBlbHxb2m");
            appmenus.setMenuAction("l3AN8HthNQwvFK5Su0OcAGjkexNkKnCICiJwPjrFMdChvXXYiH");
            appmenus.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            appmenusRepository.update(appmenus);
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("AppMenusPrimaryKey"));
            appmenusRepository.findById((java.lang.String) map.get("AppMenusPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("AppMenusPrimaryKey"));
            appmenusRepository.delete((java.lang.String) map.get("AppMenusPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateAppMenus(EntityTestCriteria contraints, AppMenus appmenus) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            appmenus.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            appmenus.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            appmenus.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            appmenusRepository.save(appmenus);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "menuTreeId", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "menuTreeId", "JvR8c2veqnCMJFlUJypZ2KOorKZE8oS1eSWVWrKA6Ln9nrOfiO7cUxoUPherXFm6MCH1WHgU4Pybj3EDm66B4N0dv7nm1wjWXloMLGK4cB0WeUCsly8yqSlBnPQHV9gtwEPaInc1rqhQpjX7e4FQDFoHuY78A65U9309Q0qxZfFEO2eSvyauzOaVEPfhJYsc3c1biqjIo5dDyKsnkD2q1XAfn20StdEFqFVzVlLzchEIMVJvGHth5sVO12nhBXWLj"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 3, "menuIcon", "Sw6cjQtnZVgFN1GL5qCBRyaKMzpIkgKrm34wPFhtOgpWcvaIMxCgcodIF6Nf220s1XAth52OkHgOIGy7g2ghQAGDm9XQiDuGigsqNingL9Qlkiyu0xed5AJhHU8osl4TSrzt345dZzLBkpmPA04997A8bSYV5g4S428NNeSviHBE2ahug3beAG16zlmqYpfur5AsT0GmZ3j2S5VXdtSTgQzWyiRrI3bYb4QbV9cZuuf2uyOSsh8Cuqgs1wyhUM0fb"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "menuAction", "HGng4ri4jDnJUUjFw7Xqp4xoT7Rcmz9eceDK1XeUrHITI8y1uSFeiS7a0tEP1sCkETSFu55VqnrYUbsZ3WXwkub2PLD1EZOCkGpQdk40gnAiakDEqRgC2sTHcxNEQXA0judoN1NFg5OJnBsBSm8lWzsEmCTld0CbnwJJw47ZlBVPLsZjpqeVHNjGeDgsFkhXwcmIu1PRKjyRnj7UlE6cFocBHgTmv4rqDZZ1tCrEfpMdVfiBJGMMmpDY3Y5UQGBT6"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 5, "menuCommands", "MbcrqpJ0DPNNLxvCHNrtC66wwHaGWpk4TJoy79unY5LCQPVOsza8btj8Ko0NB3OWI"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 6, "menuDisplay", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 7, "menuDisplay", true));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 8, "menuHead", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 9, "menuHead", true));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 10, "menuLabel", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 11, "menuLabel", "OXDvRQxqrZpi284RmTSqkhfNxJPNXzMcKI01JMBXMMklaoN43AQoHrpKsfx4vvjx6qhnrdGkx0DDDgbuj7WFmlPCyCeOzaHTqKJT2eXulRBgfeYHlwyTzQTzIC8kB7qipXmwVlAbdngRAwpreH8Z7xIGfvlizUbuB5sqEVfuWglUCMZWiH7XAxfY1rGwNKPDvZDbg1M2qQIh12yYuWY7nlQzNfHt7alJm59iVM0AHAt1h1WZyGTYYTOwzvabJ9B8d"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 12, "uiType", "jmL6"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 13, "refObjectId", "F4rWs1xNPoMpTkmMkHgM3D4ghlyDpiybGbPPwABeFrsGvHxPTPPDRgv8vRNfi89Zkd459v6UAW37Rr0b4CyJZbUyERneWRtKC03Q2jrySzYTnxZ6y8wREeLetL7hUAWqBwNpDkDk8aZUpvYZJ9f12pBpbCJb3iKTonVmt0mQNdTIfBCQ1AQrv8w9TcfzqGGtmpsykXGNsGfQTwBqJqg0dfqNKgikCCaCz1Ka4StdkztHjpVtAlR6akz6idxgT9oCi"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 14, "menuAccessRights", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 15, "menuAccessRights", 12));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 16, "appType", 3));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 17, "appId", "p6XYa1N4jAKC4v1YrQ1iyG20rI8ATBbAAy46dr7Fa36sVPJR8yQMpnXDLqolujbs93eDtGlzDHuzE3RfQA7OVcvxN8XIDuSRASmeiqVnzFp78FgV3oRQFqC7xB7SuRAnojHaP35WiHkwo228wrU1oDChD4F1WGOHsQWsvVJ0050VogRuqccCe4LHXXb8WDKqIv02uSxAO88sktuL7BGxnXwsBqrET4zFQi47YNeuzrDF1abuTpSpvSgzuso1mQJZ1"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 18, "autoSave", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 19, "autoSave", true));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                AppMenus appmenus = createAppMenus(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = appmenus.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(appmenus, null);
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 2:
                        appmenus.setMenuTreeId(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 3:
                        appmenus.setMenuIcon(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 4:
                        appmenus.setMenuAction(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 5:
                        appmenus.setMenuCommands(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 6:
                        field.setAccessible(true);
                        field.set(appmenus, null);
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 7:
                        break;
                    case 8:
                        field.setAccessible(true);
                        field.set(appmenus, null);
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 9:
                        break;
                    case 10:
                        field.setAccessible(true);
                        field.set(appmenus, null);
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 11:
                        appmenus.setMenuLabel(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 12:
                        appmenus.setUiType(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 13:
                        appmenus.setRefObjectId(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 14:
                        field.setAccessible(true);
                        field.set(appmenus, null);
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 15:
                        appmenus.setMenuAccessRights(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 16:
                        appmenus.setAppType(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 17:
                        appmenus.setAppId(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 18:
                        field.setAccessible(true);
                        field.set(appmenus, null);
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 19:
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
