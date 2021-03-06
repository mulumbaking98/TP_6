import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CopyTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test_createCopy() {
		Copy c = new Copy();
		assertNotNull("Copy is null", c);
	}
	
	@Test
	public void test_getCopyId() {
		Copy c = new Copy("1", "Fun Things", "b1", "OUT");
		assertNotNull("copy id is null", c.getId());
	}
	
	@Test
	public void test_setCopyId() {
		Copy c = new Copy();
		c.setId("1");
		assertEquals("copy id not set", "1", c.getId());
	}
			
	@Test
	public void test_checkedOut() {
		Copy c = new Copy("1", "Fun Things", "b1", "OUT");
		assertNotNull("Copy is checked out", c.getStatus());
	}
	
	@Test
	public void test_barcode() {
		Copy c = new Copy();
		c.setBarcode("1");
		assertEquals("copy barcode not set", "1", c.getBarcode());
	}
	
	@Test
	public void test_dueDate() {
		Copy c = new Copy();
		c.setDueDate("1");
		assertEquals("copy due Date not set", "1", c.getDueDate());
	}
	
//	@Test
//	public void test_set_is_checked_out() {
//		Copy c = new Copy("1", "Fun Things", "b1", "IN");
//		c.setStatus("OUT");
//		assertEquals("not checked out", true, c.getStatus());
//	}
	
}

