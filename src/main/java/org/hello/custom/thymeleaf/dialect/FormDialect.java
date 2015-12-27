package org.hello.custom.thymeleaf.dialect;

import java.util.HashSet;
import java.util.Set;

import org.hello.custom.thymeleaf.processor.field.CheckboxProcessor;
import org.hello.custom.thymeleaf.processor.field.InputProcessor;
import org.hello.custom.thymeleaf.processor.field.LabelProcessor;
import org.hello.custom.thymeleaf.processor.field.RadioProcessor;
import org.hello.custom.thymeleaf.processor.field.SelectProcessor;
import org.hello.custom.thymeleaf.processor.field.TextareaProcessor;
import org.hello.custom.thymeleaf.processor.form.FieldBoxProcessor;
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
		processor.add(new FieldBoxProcessor());
		processor.add(new CheckboxProcessor());
		processor.add(new InputProcessor());
		processor.add(new LabelProcessor());
		processor.add(new RadioProcessor());
		processor.add(new SelectProcessor());
		processor.add(new TextareaProcessor());
		return processor;
	}

}
