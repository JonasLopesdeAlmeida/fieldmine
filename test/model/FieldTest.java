package model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.jmmd.model.Field;

public class FieldTest {
	private Field field;

	@BeforeEach
	void IntiateFieldValue(){
	  field = new Field(3, 3);
	}
	
	@Test
	void VerifyifNeighborWasAddDistanceEqual1onTheLeftSide() {
		//cenario
		Field neighbor = new Field(3, 2);
		
		//action
	    boolean result = field.AddNeighbor(neighbor);
	    
	    //verify
	    assertTrue(result);
	}
	
	@Test
	void VerifyifNeighborWasAddDistanceEqual1onTheRightSide() {
		//cenario
		Field neighbor = new Field(3, 4);
		
		//action
	    boolean result = field.AddNeighbor(neighbor);
	    
	    //verify
	    assertTrue(result);
	}
	
	@Test
	void VerifyifNeighborWasAddDistanceEqual1Above() {
		//cenario
		Field neighbor = new Field(2, 3);
		
		//action
	    boolean result = field.AddNeighbor(neighbor);
	    
	    //verify
	    assertTrue(result);
	}
	
	
	@Test
	void VerifyifNeighborWasAddDistanceEqual1Below() {
		//cenario
		Field neighbor = new Field(4, 3);
		
		//action
	    boolean result = field.AddNeighbor(neighbor);
	    
	    //verify
	    assertTrue(result);
	}
	
	
	@Test
	void VerifyifNeighborWasAddDistanceEqual2isDiagonal() {
		//cenario
		Field neighbor = new Field(2, 2);
		
		//action
	    boolean result = field.AddNeighbor(neighbor);
	    
	    //verify
	    assertTrue(result);
	}
	
	@Test
	void VerifyifisNotNeighbor() {
		//cenario
		Field neighbor = new Field(1, 1);
		
		//action
	    boolean result = field.AddNeighbor(neighbor);
	    
	    //verify
	    assertFalse(result);
	}
	
	
}
