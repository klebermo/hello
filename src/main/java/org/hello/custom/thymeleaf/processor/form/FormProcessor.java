package org.hello.custom.thymeleaf.processor.form;

import java.lang.reflect.Field;
import java.util.Map;

import org.thymeleaf.Arguments;
import org.thymeleaf.dom.Attribute;
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
		String classe = element.getAttributeValue("classe");
		
		Class<?> clazz;
		try {
			clazz = Class.forName("org.hello.model." + classe.toLowerCase() + "." + classe);
		} catch (ClassNotFoundException e) {
			clazz = null;
		}
		
		element.removeAttribute("class");
		
		if(clazz != null) {
			Element form = new Element("form");
			
			for(Field field : clazz.getDeclaredFields()) {
				for(Node child : element.getElementChildren()) {
					//child.setNodeLocalVariable("field", field);
					child.setNodeProperty("field", field);
					child.setProcessable(true);
					form.addChild(child);
				}
			}
			
			for( Map.Entry<String, Attribute> entry : element.getAttributeMap().entrySet() )
			      form.setAttribute(entry.getKey(), entry.getValue().getValue());
			
			element.getParent().insertBefore(element, form);
			element.getParent().removeChild(element);
		}
		
		return ProcessorResult.OK;
	}

	@Override
	public int getPrecedence() {
		return 0;
	}

}
