package org.hello.custom.thymeleaf.processor.form;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import org.thymeleaf.Arguments;
import org.thymeleaf.dom.Element;
import org.thymeleaf.dom.Node;
import org.thymeleaf.processor.ProcessorResult;
import org.thymeleaf.processor.element.AbstractElementProcessor;

public class FieldBoxProcessor extends AbstractElementProcessor {

	public FieldBoxProcessor() {
		super("field-box");
	}

	@Override
	protected ProcessorResult processElement(Arguments arguments, Element element) {
		String type = element.getAttributeValue("type");
		Field field = (Field) arguments.getLocalVariable("field");
		
		for(Annotation annotation : field.getAnnotations()) {
			if(annotation.annotationType().getSimpleName().equals(type)) {
				Element fieldbox = new Element("field-box");
				
				for(Node child : element.getChildren()) {
					child.setNodeLocalVariable("field", field);
					child.setProcessable(true);
					fieldbox.addChild(child);
				}
				
				element.getParent().insertBefore(element, fieldbox);
				element.getParent().removeChild(element);
			}
		}
		
		return ProcessorResult.OK;
	}

	@Override
	public int getPrecedence() {
		return 0;
	}

}
