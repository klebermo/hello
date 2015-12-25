package org.hello.generic.persistence;

public abstract class Model extends Object {
	
	public abstract String getId();
	
	public boolean equals(Model object) {
		return this.getId().equals(object.getId());
	}
	
	public abstract String toString();
	
}
