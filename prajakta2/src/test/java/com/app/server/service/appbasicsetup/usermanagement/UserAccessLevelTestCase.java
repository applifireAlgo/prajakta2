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
import com.app.server.repository.appbasicsetup.usermanagement.UserAccessLevelRepository;
import com.app.shared.appbasicsetup.usermanagement.UserAccessLevel;
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
public class UserAccessLevelTestCase extends EntityTestCriteria {

    @Autowired
    private UserAccessLevelRepository<UserAccessLevel> useraccesslevelRepository;

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

    private UserAccessLevel createUserAccessLevel(Boolean isSave) throws Exception {
        UserAccessLevel useraccesslevel = new UserAccessLevel();
        useraccesslevel.setUserAccessLevel(valueGenerator.getRandomInteger(99999, 0));
        useraccesslevel.setLevelName("CrwsChLmS5gV2HQT6JTQhy9i9qX88rjxjaZIDRWvPJXEBoR06X");
        useraccesslevel.setLevelDescription("hzJswMnPS3csMvm4Vu2McohlHqy1sbE22DZL7n5QS9f5zbATcE");
        useraccesslevel.setLevelHelp("pSot6LdoXFYaNg7E4eV77wRmpgHjdCDiCzwGeZhou4TXR6LEwP");
        useraccesslevel.setLevelIcon("4WwmgeijxhEzcxj7MD07gHqOKATwAzLJXDoElBwoigdP3HqWAv");
        useraccesslevel.setEntityValidator(entityValidator);
        return useraccesslevel;
    }

    @Test
    public void test1Save() {
        try {
            UserAccessLevel useraccesslevel = createUserAccessLevel(true);
            useraccesslevel.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            useraccesslevel.isValid();
            useraccesslevelRepository.save(useraccesslevel);
            map.put("UserAccessLevelPrimaryKey", useraccesslevel._getPrimarykey());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("UserAccessLevelPrimaryKey"));
            UserAccessLevel useraccesslevel = useraccesslevelRepository.findById((java.lang.String) map.get("UserAccessLevelPrimaryKey"));
            useraccesslevel.setUserAccessLevel(12018);
            useraccesslevel.setLevelName("VMmq8SJJy2FucrweFsJOfxlfAnTlBkGeVmTuIRBdAsAvI109go");
            useraccesslevel.setLevelDescription("o15aookGIcJitkSfXG3yKSLqGrHa902m4DVY5ojgF2mvgT1gQp");
            useraccesslevel.setVersionId(1);
            useraccesslevel.setLevelHelp("CgJsVOvVN5SPt9lrMFbRQPHYpDSLIPi8pJIfrCeWpvSHFRj6xl");
            useraccesslevel.setLevelIcon("02e6AhFFCPhulTecswihzxu7gGHy8By9MR03W4g19FDaHH8e2l");
            useraccesslevel.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            useraccesslevelRepository.update(useraccesslevel);
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("UserAccessLevelPrimaryKey"));
            useraccesslevelRepository.findById((java.lang.String) map.get("UserAccessLevelPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("UserAccessLevelPrimaryKey"));
            useraccesslevelRepository.delete((java.lang.String) map.get("UserAccessLevelPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateUserAccessLevel(EntityTestCriteria contraints, UserAccessLevel useraccesslevel) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            useraccesslevel.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            useraccesslevel.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            useraccesslevel.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            useraccesslevelRepository.save(useraccesslevel);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "userAccessLevel", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "userAccessLevel", 161898));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 3, "levelName", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "levelName", "2qED5XBVjvegnwKV92Z2hMvXJbINixFfbTdAiNSZoGPuiMiLgIvYY9bjxd8bGbqpneoLdMoPCmmee0FJ56Uf996tbhP4MBYPtEPN9Fs2JD9HwJN7QpIPsdNu2bKndBEV8QskvnbUurX2nSs8xsHv7XssW15FE7g44upLMHPabS8jUtDqKw9EwZwlPtFxBBnixJGggHgox8UhPLOJHWLLWwNHAUUC351NEuOhpDn4ds0Auh3tAtijidsqC3DnHfVdA"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 5, "levelDescription", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "levelDescription", "GNVZy8TfxmUpzFqSXYO3bwFddYcrz7KT9iR1HbB77nT3YLG90GBHdErzwJqqt4c7jT5zZW71Su7P7h3dwQbKljo7F0M2l46IY5tZvegEuBNuAZ23jV9OYDip9K7fReo9xCH9vfdp76hEGY3jYtss9QLaWQkzzQyFa1oGazjcCVIVMMONVXQHi1o8SIv4GpiQxZXg7fOh1UJOSB71e7ouxQZHAi3Y44aCFScomyZfnJcM5Qc1sf3e57NKtlpads14E"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 7, "levelHelp", "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"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 8, "levelIcon", "fPr66ZAH3zRu29EGX7oP5DQYYLsu2o04rOlVBdi8itjELocMTN1iXBxzVGArbQZhKxzOadkZikMlrmKITOivR7fySpdEFTIbtgUBCogllm1CSr6eYBH0hJyTue4LghpCVUTK3MmqZplxuMovMRVqDxIfqu4cIH4Q3HQwKtiXhKAnIJzjq53vXh3wfcaqoMGtLZgQ2820PCtzkcNBRHbGjgOhi0ApC2FTWLAp67LC8M3iuo4jh3y6dlBWFqtD41i57"));
        entityContraints.add(new EntityTestCriteria(UNIQUE, 9, "CombineUniqueKey", ""));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        UserAccessLevel useraccesslevelUnique = useraccesslevelRepository.findById((java.lang.String) map.get("UserAccessLevelPrimaryKey"));
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                UserAccessLevel useraccesslevel = createUserAccessLevel(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = useraccesslevel.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(useraccesslevel, null);
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 2:
                        useraccesslevel.setUserAccessLevel(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 3:
                        field.setAccessible(true);
                        field.set(useraccesslevel, null);
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 4:
                        useraccesslevel.setLevelName(contraints.getNegativeValue().toString());
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 5:
                        field.setAccessible(true);
                        field.set(useraccesslevel, null);
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 6:
                        useraccesslevel.setLevelDescription(contraints.getNegativeValue().toString());
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 7:
                        useraccesslevel.setLevelHelp(contraints.getNegativeValue().toString());
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 8:
                        useraccesslevel.setLevelIcon(contraints.getNegativeValue().toString());
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 9:
                        useraccesslevel.setUserAccessLevel(useraccesslevelUnique.getUserAccessLevel());
                        validateUserAccessLevel(contraints, useraccesslevel);
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
