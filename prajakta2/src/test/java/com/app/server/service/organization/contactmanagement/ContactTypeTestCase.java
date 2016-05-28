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
import com.app.server.repository.organization.contactmanagement.ContactTypeRepository;
import com.app.shared.organization.contactmanagement.ContactType;
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
public class ContactTypeTestCase extends EntityTestCriteria {

    @Autowired
    private ContactTypeRepository<ContactType> contacttypeRepository;

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

    private ContactType createContactType(Boolean isSave) throws Exception {
        ContactType contacttype = new ContactType();
        contacttype.setContactTypeDesc("gSmgjgZ7N9YSprvGGQq4Sp6tPPdgwiR22iniWSxvo2AFzVJs3k");
        contacttype.setContactType("eH73bG26t0uLaVLTvhJzSU5kBNj3tw3fAaiydP0TqdA30qfNik");
        contacttype.setContactTypeIcon("G5ndYDLYJmms2M0CV7mJ2wNVrtLvKPgnj5uxhTwq78Zh4uStHb");
        contacttype.setEntityValidator(entityValidator);
        return contacttype;
    }

    @Test
    public void test1Save() {
        try {
            ContactType contacttype = createContactType(true);
            contacttype.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            contacttype.isValid();
            contacttypeRepository.save(contacttype);
            map.put("ContactTypePrimaryKey", contacttype._getPrimarykey());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("ContactTypePrimaryKey"));
            ContactType contacttype = contacttypeRepository.findById((java.lang.String) map.get("ContactTypePrimaryKey"));
            contacttype.setContactTypeDesc("ZRWCvWke3rmscMyMYPjVlpifgfFJHmAQ9wQ3E2gYVxy6CJwOgo");
            contacttype.setContactType("ebiXkzdZYr2KeIsaAfytqJc26JmATatp0AXgY2c1r0Up7sP2uL");
            contacttype.setContactTypeIcon("J9LWPTffFLcjeHrwgIuusgmNldYJcCmhlUMsNziR1soBbhZ5na");
            contacttype.setVersionId(1);
            contacttype.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            contacttypeRepository.update(contacttype);
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("ContactTypePrimaryKey"));
            contacttypeRepository.findById((java.lang.String) map.get("ContactTypePrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("ContactTypePrimaryKey"));
            contacttypeRepository.delete((java.lang.String) map.get("ContactTypePrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateContactType(EntityTestCriteria contraints, ContactType contacttype) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            contacttype.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            contacttype.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            contacttype.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            contacttypeRepository.save(contacttype);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "contactType", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "contactType", "2iRWAQpfFA2ImKl99jgB22KLmbsAfk1O1lhq4t6Y43OPN6ZW7IBftWQw7LEldoxtRzBYOMvCDILx1fsSpNNoto8xRZAnnjjlHsWnFXqnIGM24ac1JZ0QYIGTH7Dw5pHbF"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 3, "contactTypeDesc", "UFKl8xfiwxBqypAExf0sKk6cjoFlXzpKjv6W6x7x21r5TQfEd9Tf55THRPgM7dsf16MpBABeSSJQxvxzEe3j5jIZqVsIPS5cHe3GlaieIlCHBWV4cjJ2P5K81WPjshES7s28DW42mHZshnvqXJPZNxB9Dd0Hx6TkEzBKNW21S8hdQfLNHIZMltW7gvYeW7arHQVwZigltr4VHC3aS5gl0wWf8mubVubwuSIQndOFUrP83m3CkvBTSLZqh0ZOz5ss6"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "contactTypeIcon", "frNZLWoBDoNbml4iBtYcRBscL4hGHT3ogntduQxynwxykD3XHXurhE4iHI6GIqmqwVgYOIhXOFnREga5YmNp4O1Edaz7MBjCTFeAHCBWDCoNwBcc5Dd65vYe8Zu5orsHv"));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                ContactType contacttype = createContactType(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = contacttype.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(contacttype, null);
                        validateContactType(contraints, contacttype);
                        failureCount++;
                        break;
                    case 2:
                        contacttype.setContactType(contraints.getNegativeValue().toString());
                        validateContactType(contraints, contacttype);
                        failureCount++;
                        break;
                    case 3:
                        contacttype.setContactTypeDesc(contraints.getNegativeValue().toString());
                        validateContactType(contraints, contacttype);
                        failureCount++;
                        break;
                    case 4:
                        contacttype.setContactTypeIcon(contraints.getNegativeValue().toString());
                        validateContactType(contraints, contacttype);
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
