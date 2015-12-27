package org.hello.custom.thymeleaf.processor.field;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Map;

import org.thymeleaf.Arguments;
import org.thymeleaf.dom.Attribute;
import org.thymeleaf.dom.Element;
import org.thymeleaf.processor.ProcessorResult;
import org.thymeleaf.processor.element.AbstractElementProcessor;

public class InputProcessor extends AbstractElementProcessor {

	public InputProcessor() {
		super("input");
	}

	@Override
	public ProcessorResult processElement(Arguments arguments, Element element) {
		Field field = (Field) arguments.getLocalVariable("field");
		
		Element node = new Element("input");
		for(Annotation annotation : field.getAnnotations())
			if(!annotation.annotationType().getSimpleName().equals("Column") && !annotation.annotationType().getSimpleName().equals("Input"))
				node.setAttribute("type", annotation.annotationType().getSimpleName().toLowerCase());
		node.setAttribute("name", field.getName());
		node.setAttribute("value", null);
		for( Map.Entry<String, Attribute> entry : element.getAttributeMap().entrySet() )
		      node.setAttribute(entry.getKey(), entry.getValue().getValue());
		element.getParent().insertBefore(element, node);
		element.getParent().removeChild(element);
		
		return ProcessorResult.OK;
	}

	@Override
	public int getPrecedence() {
		return 0;
	}

}
