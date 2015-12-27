package org.hello.custom.thymeleaf.processor.field;

import java.lang.reflect.Field;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import org.thymeleaf.Arguments;
import org.thymeleaf.dom.Attribute;
import org.thymeleaf.dom.Element;
import org.thymeleaf.dom.Text;
import org.thymeleaf.processor.ProcessorResult;
import org.thymeleaf.processor.element.AbstractElementProcessor;

public class LabelProcessor extends AbstractElementProcessor {

	public LabelProcessor() {
		super("label");
	}

	@Override
	public ProcessorResult processElement(Arguments arguments, Element element) {
		Locale currentLocale = Locale.getDefault();
		ResourceBundle messages = ResourceBundle.getBundle("messages", currentLocale);
		Field field = (Field) arguments.getLocalVariable("field");
		
		Element node = new Element("label");
		node.addChild(new Text(messages.getString(field.getName())));
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
