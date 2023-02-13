package com.exercice.supermarket.exceptions;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class CheckClassErrors {
	
	private CheckClassErrors() {}
	
	public static final boolean checkHasAllFields(Class<?> c, Object o) {
		Set<Field> fields = new HashSet<>(Arrays.asList(c.getFields()));
		Set<Field> objectFields = new HashSet<>(Arrays.asList(o.getClass().getFields()));
		if (objectFields.containsAll(fields)) {
			return true;
		}
		return false;
	}
	
	
	
}
