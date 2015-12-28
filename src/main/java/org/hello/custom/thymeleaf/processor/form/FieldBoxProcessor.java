package org.hello.custom.thymeleaf.processor.form;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Map;

import org.thymeleaf.Arguments;
import org.thymeleaf.dom.Attribute;
import org.thymeleaf.dom.Element;
import org.thymeleaf.dom.Node;
import org.thymeleaf.processor.element.AbstractConditionalVisibilityElementProcessor;

public class FieldBoxProcessor extends AbstractConditionalVisibilityElementProcessor {

	public FieldBoxProcessor() {
		super("field-box");
	}

	@Override
	public boolean isVisible(Arguments arguments, Element element) {
		String type = element.getAttributeValue("type");
		element.removeAttribute("type");
		Field field = (Field) arguments.getLocalVariable("field");
		
		if(field == null)
			return false;
		else {
			Class<? extends Annotation> clazz;
			try {
				clazz = (Class<? extends Annotation>) Class.forName("org.hello.custom.annotations.form." + type);
			} catch (ClassNotFoundException e) {
				clazz = null;
			}
			if(field.isAnnotationPresent(clazz)) {
				Element fieldbox = new Element("field-box");
				
				for(Node child : element.getChildren()) {
					child.setNodeLocalVariable("field", field);
					child.setProcessable(true);
					fieldbox.addChild(child);
				}
				
				for( Map.Entry<String, Attribute> entry : element.getAttributeMap().entrySet() )
				      fieldbox.setAttribute(entry.getKey(), entry.getValue().getValue());
				
				element.getParent().insertBefore(element, fieldbox);
				element.getParent().removeChild(element);
				
				return true;
			} else
				return false;
		}
	}

	@Override
	public boolean removeHostElementIfVisible(Arguments arguments, Element element) {
		return false;
	}

	@Override
	public int getPrecedence() {
		return 0;
	}

}
