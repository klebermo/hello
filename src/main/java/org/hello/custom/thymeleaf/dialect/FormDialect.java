package org.hello.custom.thymeleaf.dialect;

import java.util.HashSet;
import java.util.Set;

import org.hello.custom.thymeleaf.processor.form.FormProcessor;
import org.thymeleaf.dialect.AbstractDialect;
import org.thymeleaf.processor.IProcessor;

public class FormDialect extends AbstractDialect {

	public String getPrefix() {
		return null;
	}
	
	public Set<IProcessor> getProcessors() {
		final Set<IProcessor> processor = new HashSet<IProcessor>();
		processor.add(new FormProcessor());
		return processor;
	}

}
