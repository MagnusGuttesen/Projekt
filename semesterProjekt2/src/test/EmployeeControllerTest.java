package test;

import controller.EmployeeController;
import db.DataAccessException;
import model.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeControllerTest {

    private EmployeeController controller;

    @BeforeEach
    void setUp() throws DataAccessException {
        controller = new EmployeeController();
    }

    @Test
    void testFindAll_ShouldReturnEmployees() throws DataAccessException {
        List<Employee> employees = controller.findAll();
        assertNotNull(employees);
        assertTrue(employees.size() > 0, "Der burde være mindst én medarbejder");
    }

    @Test
    void testFindByName_ShouldReturnMatchingEmployees() throws DataAccessException {
        String name = "morten"; // Antag navn findes i databasen
        List<Employee> employees = controller.findByName(name);
        assertNotNull(employees);
        assertTrue(employees.stream().anyMatch(e -> e.getName().toLowerCase().contains(name)));
    }

    @Test
    void testFindByEmail_ValidEmail_ShouldReturnEmployee() throws DataAccessException {
        String email = "morten1234@gmail.com"; // Antag email findes i databasen
        Employee employee = controller.findByEmail(email);
        assertNotNull(employee);
        assertEquals(email, employee.getEmail());
    }

    @Test
    void testFindByEmail_InvalidEmail_ShouldReturnNull() throws DataAccessException {
        String email = "doesnotexist@example.com";
        Employee employee = controller.findByEmail(email);
        assertNull(employee);
    }

   

    @Test
    void testFindEmployeeById_ValidId_ShouldReturnEmployee() {
        String id = "1"; // Antag medarbejderid "1" eksisterer
        Employee employee = controller.findEmployeeById(id);
        assertNotNull(employee);
        assertEquals(id, employee.getEmployeeId());
    }

    @Test
    void testFindEmployeeById_InvalidId_ShouldReturnNull() {
        String id = "9999";
        Employee employee = controller.findEmployeeById(id);
        assertNull(employee);
    }

    @Test
    void testInsert_ShouldReturnInsertedEmployee() throws DataAccessException {
        Employee e = new Employee();
        e.setEmployeeId("888");
        e.setPassword("test123");
        e.setName("Test Medarbejder");
        e.setEmail("test@example.com");

        Employee inserted = controller.insert(e);
        assertNotNull(inserted);
        assertEquals("888", inserted.getEmployeeId());
        assertEquals("Test Medarbejder", inserted.getName());
    }
}
