package br.com.jmmd.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
	
	@Test
	void VerifyOpenWithNeigbors() {
		
		Field field11 = new Field(1, 1);
		Field field22 = new Field(2,2);
		
		field22.AddNeighbor(field11);
		
		field.AddNeighbor(field22);
		field.open();
		
		assertTrue(field22.isOpen() && field11.isOpen());
	}
	
	@Test
	void VerifyOpenWithNeigbors2() {
		
		Field field11 = new Field(1, 1);
		Field field12 = new Field(1,1);
		field12.mined();
		
		Field field22 = new Field(2, 2);
		
		field22.AddNeighbor(field11);
		field22.AddNeighbor(field12);
		
		field.AddNeighbor(field22);
		field.open();
		
		assertTrue(field22.isOpen() && field11.isClosed());
	}
	
}
