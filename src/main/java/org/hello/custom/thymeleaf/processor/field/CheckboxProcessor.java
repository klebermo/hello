package org.hello.custom.thymeleaf.processor.field;

import java.lang.reflect.Field;
import java.util.Map;

import org.thymeleaf.Arguments;
import org.thymeleaf.dom.Attribute;
import org.thymeleaf.dom.Element;
import org.thymeleaf.dom.Text;
import org.thymeleaf.processor.ProcessorResult;
import org.thymeleaf.processor.element.AbstractElementProcessor;

public class CheckboxProcessor extends AbstractElementProcessor {

	public CheckboxProcessor() {
		super("checkbox");
	}

	@Override
	protected ProcessorResult processElement(Arguments arguments, Element element) {
		Field field = (Field) arguments.getLocalVariable("field");
		
		Element node = new Element("input");
		node.setAttribute("type", "checkbox");
		node.setAttribute("name", field.getName());
		node.setAttribute("value", null);
		node.addChild(new Text("..."));
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
