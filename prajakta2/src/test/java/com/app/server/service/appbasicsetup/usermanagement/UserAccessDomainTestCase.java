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
import com.app.server.repository.appbasicsetup.usermanagement.UserAccessDomainRepository;
import com.app.shared.appbasicsetup.usermanagement.UserAccessDomain;
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
public class UserAccessDomainTestCase extends EntityTestCriteria {

    @Autowired
    private UserAccessDomainRepository<UserAccessDomain> useraccessdomainRepository;

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

    private UserAccessDomain createUserAccessDomain(Boolean isSave) throws Exception {
        UserAccessDomain useraccessdomain = new UserAccessDomain();
        useraccessdomain.setUserAccessDomain(valueGenerator.getRandomInteger(99999, 0));
        useraccessdomain.setDomainName("IArmpqBPZlFGJFbFjM2QeJluvzKRRrRTbVyNKVi9BJ4JypFcyT");
        useraccessdomain.setDomainHelp("wCK26Ikh1Tfc3Oofx9BBr8yVbZyfDT4eYiEbPp8tdvxaxCOq01");
        useraccessdomain.setDomainIcon("ljXtS91LyB8JdAQXarj1howcAEBOZgcioEh2Sqk4TQ70po5mjG");
        useraccessdomain.setDomainDescription("as4dEKoKAL9npI5oYkVQhtoNiNC286lAuFEMPcQbjJd8AG5Gpy");
        useraccessdomain.setEntityValidator(entityValidator);
        return useraccessdomain;
    }

    @Test
    public void test1Save() {
        try {
            UserAccessDomain useraccessdomain = createUserAccessDomain(true);
            useraccessdomain.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            useraccessdomain.isValid();
            useraccessdomainRepository.save(useraccessdomain);
            map.put("UserAccessDomainPrimaryKey", useraccessdomain._getPrimarykey());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("UserAccessDomainPrimaryKey"));
            UserAccessDomain useraccessdomain = useraccessdomainRepository.findById((java.lang.String) map.get("UserAccessDomainPrimaryKey"));
            useraccessdomain.setUserAccessDomain(50157);
            useraccessdomain.setDomainName("HH0fXCDAsCp4Fs4gRvMqRKjslwjWgJlK2RTOEsnmTkBzuppdvA");
            useraccessdomain.setVersionId(1);
            useraccessdomain.setDomainHelp("aWtlutiaxyL1RUcg8Hz85t4WmNOA0vfQ5gWl16M56O8JSBUfqw");
            useraccessdomain.setDomainIcon("gFCiMyM52WhtXyKWpvMupZALkgJyZTl9PNbuOZbecTNJDPl5jJ");
            useraccessdomain.setDomainDescription("gx4G3nsqpLvfy6j0MZVs7eKnTKUjMPaW5z4S1c2SVlMl4zkSrR");
            useraccessdomain.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            useraccessdomainRepository.update(useraccessdomain);
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("UserAccessDomainPrimaryKey"));
            useraccessdomainRepository.findById((java.lang.String) map.get("UserAccessDomainPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("UserAccessDomainPrimaryKey"));
            useraccessdomainRepository.delete((java.lang.String) map.get("UserAccessDomainPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateUserAccessDomain(EntityTestCriteria contraints, UserAccessDomain useraccessdomain) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            useraccessdomain.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            useraccessdomain.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            useraccessdomain.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            useraccessdomainRepository.save(useraccessdomain);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "userAccessDomain", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "userAccessDomain", 193516));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 3, "domainName", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "domainName", "MBcDXbqKR4cNG5gMp1upK5s6YvXt9sNLdcwEmKpLomVKilCnv38Zp5XjeBcUD6xzxbXBkXrkXgyI9eCwxuf26bYEz4chg64uaHpX7ZCRDMXmuNJ8ZD18zmMoLZbDVHWaMouJlus7niCY1L8sMUgpfp3CNPzxaEOvCwArhXZJPTZiYc0dSr4gBO8AV48WJY4zAMBeE95N57InE5eDZ9fSvodm9aqY8zCln8hk3ylBokdA8NVXIzeRrgrBMXzwtYvQ2"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 5, "domainDescription", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "domainDescription", "MpxQX4yW4ZXvw07YaxlSGLvYaF1IK1ggQzaNTbcHscpVnefDOT3T9O4u7Th2pAPAnkCxm0FWwdjgVjf5KyZat9oysQR08JTxHmpPFAHN4AANwAt319PkjlbW4UPJo1qMs0ufTrk6Hb9I7pqJX6DCeR9zxy2LWtzg9wIVu44bvDXUn5U4FDuU3assp4CUCGQCwoW2V74ANaMaRcWK0SACtbp97Gh85aP9uQl1VJc1HPjSVd3pd0iLkvi8Nz0ATBCZH"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 7, "domainHelp", "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"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 8, "domainIcon", "eXC3DSghUw1TEJSDCT3igcRp0NprnDQebmXiNhFGU8SQ5y1Zb1xQI5YZ0UXYsuf0HynCRRT0NsHgIe0zVid7f0QjNoliDVVKjuWfQjOLNCvYlfUcUc5bpWHKQHx4TJ9oiP7ki0kLDlD1QV27BIBnCoNqq7c0B0nM94nw1cOiAcn4H75UdKohWLf3Jsj4gAkw4H9BVdUyDDoOs6dlRVjqpK81uFK5okEfSCnTYNG7dTTG0Ot0asTQToFvHnMxOchOx"));
        entityContraints.add(new EntityTestCriteria(UNIQUE, 9, "CombineUniqueKey", ""));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        UserAccessDomain useraccessdomainUnique = useraccessdomainRepository.findById((java.lang.String) map.get("UserAccessDomainPrimaryKey"));
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                UserAccessDomain useraccessdomain = createUserAccessDomain(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = useraccessdomain.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(useraccessdomain, null);
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 2:
                        useraccessdomain.setUserAccessDomain(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 3:
                        field.setAccessible(true);
                        field.set(useraccessdomain, null);
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 4:
                        useraccessdomain.setDomainName(contraints.getNegativeValue().toString());
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 5:
                        field.setAccessible(true);
                        field.set(useraccessdomain, null);
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 6:
                        useraccessdomain.setDomainDescription(contraints.getNegativeValue().toString());
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 7:
                        useraccessdomain.setDomainHelp(contraints.getNegativeValue().toString());
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 8:
                        useraccessdomain.setDomainIcon(contraints.getNegativeValue().toString());
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 9:
                        useraccessdomain.setUserAccessDomain(useraccessdomainUnique.getUserAccessDomain());
                        validateUserAccessDomain(contraints, useraccessdomain);
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
