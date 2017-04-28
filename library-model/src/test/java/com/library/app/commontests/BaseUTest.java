package com.library.app.commontests;

import com.library.app.common.constants.PersistenceConstants;
import com.library.app.commontests.util.DBCommandTransactionalExecutor;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Optional;

@Ignore
public class BaseUTest {

    protected static EntityManagerFactory emf;
    protected static EntityManager em;
    protected static DBCommandTransactionalExecutor dbCommandTransactionalExecutor;

    @BeforeClass
    public static void initTestCase() {
        emf = Persistence.createEntityManagerFactory(PersistenceConstants.PERSISTENCE_UNIT_NAME);
        em = emf.createEntityManager();
        dbCommandTransactionalExecutor = new DBCommandTransactionalExecutor(em);
    }

    @AfterClass
    public static void closeEntityManager() {
        Optional.of(em).get().close();
        Optional.of(emf).get().close();
    }

}
