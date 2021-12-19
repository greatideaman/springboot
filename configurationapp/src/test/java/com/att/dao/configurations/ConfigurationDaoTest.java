package com.att.dao.configurations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.att.data.configurations.ConfigValue;

class ConfigurationDaoTest {

    private ConfigurationDao dao;

    @BeforeEach
    void setUp() throws Exception {
        dao = new ConfigurationDao();
        dao.removeAllConfigurations();
        dao.addConfiguration("012018", new ConfigValue("A", 123));
        dao.addConfiguration("012018", new ConfigValue("B", 124));
        dao.addConfiguration("012018", new ConfigValue("C", 125));
        dao.addConfiguration("012018", new ConfigValue("C", 126));
        dao.addConfiguration("022018", new ConfigValue("A", 5));
        dao.addConfiguration("022018", new ConfigValue("C", 4));
        dao.addConfiguration("022018", new ConfigValue("F", 2));
        dao.addConfiguration("022018", new ConfigValue("G", 3));
        dao.addConfiguration("022018", new ConfigValue("H", 1));
        dao.addConfiguration("032018", new ConfigValue("Some Config", 3));
    }

    @AfterEach
    void tearDown() throws Exception {
    }

    @Test
    void testGetConfigurationsForYearMonth() {

        assertThrows(IllegalArgumentException.class, () -> {
            dao.addConfiguration("012018", null);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            dao.addConfiguration(null, new ConfigValue("something", 3));
        });

        List<ConfigValue> list012018 = dao.getConfigurationsForYearMonth("012018");
        assertNotNull(list012018);
        assertEquals(list012018.size(), 4);

        List<ConfigValue> list022018 = dao.getConfigurationsForYearMonth("022018");
        assertNotNull(list022018);
        assertTrue(list022018.size() == 5);

    }

    @Test
    void testRemoveAllConfigurationsForYearMonth() {
        dao.removeAllConfigurationsForYearMonth("012018");
        assertNull(dao.getConfigurationsForYearMonth("012018"));
        assertNotNull(dao.getConfigurationsForYearMonth("022018"));
    }

    @Test
    void testGetCurrentConfigurations() {
        assertNotNull(dao.getCurrentConfigurations());
        assertEquals(dao.getCurrentConfigurations().size(), 3);
    }

    @Test
    void testRemoveConfiguration() {
        List<ConfigValue> list012018 = dao.getConfigurationsForYearMonth("012018");
        assertNotNull(list012018);
        assertEquals(list012018.size(), 4);
        dao.removeConfiguration("012018", 124);
        list012018 = dao.getConfigurationsForYearMonth("012018");
        assertEquals(list012018.size(), 3);
    }

}
