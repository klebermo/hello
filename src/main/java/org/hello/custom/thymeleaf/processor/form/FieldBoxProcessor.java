package org.hello.custom.thymeleaf.processor.form;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.List;
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
		
		Class<? extends Annotation> clazz;
		try {
			clazz = (Class<? extends Annotation>) Class.forName("org.hello.custom.annotations.form." + type);
		} catch (ClassNotFoundException e) {
			clazz = null;
		}
		
		element.removeAttribute("type");
		
		Field f = (Field) arguments.getLocalVariable("field");
		if(f.isAnnotationPresent(clazz)) {
			Element fieldbox = new Element("field-box");
			
			for(Node child : element.getChildren()) {
				child.setNodeLocalVariable("field", f);
				child.setProcessable(true);
				fieldbox.addChild(child);
			}
			
			for( Map.Entry<String, Attribute> entry : element.getAttributeMap().entrySet() )
			      fieldbox.setAttribute(entry.getKey(), entry.getValue().getValue());
			
			element.getParent().insertBefore(element, fieldbox);
			
			Element br = new Element("br");
			element.getParent().insertBefore(element, br);
			
			element.getParent().removeChild(element);
			
			return true;
		}
		
		return false;
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
