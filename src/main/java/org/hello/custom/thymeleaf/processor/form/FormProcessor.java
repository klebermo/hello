package org.hello.custom.thymeleaf.processor.form;

import java.lang.reflect.Field;

import org.thymeleaf.Arguments;
import org.thymeleaf.dom.Element;
import org.thymeleaf.dom.Node;
import org.thymeleaf.processor.ProcessorResult;
import org.thymeleaf.processor.element.AbstractElementProcessor;

public class FormProcessor extends AbstractElementProcessor {

	public FormProcessor() {
		super("form");
	}

	@Override
	public ProcessorResult processElement(Arguments arguments, Element element) {
		String classe = element.getAttributeValue("class");
		
		Element form = new Element("form");
		
		try {
			Class<?> clazz = Class.forName("org.hello.model." + classe.toLowerCase() + "." + classe);
			for(Field field : clazz.getFields()) {
				for(Node child : element.getChildren()) {
					child.setNodeLocalVariable("field", field);
					child.setProcessable(true);
					form.addChild(child);
				}
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		element.getParent().insertBefore(element, form);
		element.getParent().removeChild(element);
		
		return ProcessorResult.OK;
	}

	@Override
	public int getPrecedence() {
		return 0;
	}

}
