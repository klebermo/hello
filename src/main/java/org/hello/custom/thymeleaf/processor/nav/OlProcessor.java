package org.hello.custom.thymeleaf.processor.nav;

import org.thymeleaf.Arguments;
import org.thymeleaf.dom.Element;
import org.thymeleaf.processor.ProcessorResult;
import org.thymeleaf.processor.element.AbstractElementProcessor;

public class OlProcessor extends AbstractElementProcessor {

	public OlProcessor() {
		super("ol");
	}

	@Override
	public ProcessorResult processElement(Arguments arguments, Element element) {
		return ProcessorResult.OK;
	}

	@Override
	public int getPrecedence() {
		return 0;
	}

}
