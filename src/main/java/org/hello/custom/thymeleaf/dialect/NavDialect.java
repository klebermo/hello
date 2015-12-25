package org.hello.custom.thymeleaf.dialect;

import java.util.HashSet;
import java.util.Set;

import org.hello.custom.thymeleaf.processor.nav.OlProcessor;
import org.hello.custom.thymeleaf.processor.nav.UlProcessor;
import org.thymeleaf.dialect.AbstractDialect;
import org.thymeleaf.processor.IProcessor;

public class NavDialect extends AbstractDialect {

	public String getPrefix() {
		return null;
	}
	
	public Set<IProcessor> getProcessors() {
		final Set<IProcessor> processor = new HashSet<IProcessor>();
		processor.add(new OlProcessor());
		processor.add(new UlProcessor());
		return processor;
	}

}
