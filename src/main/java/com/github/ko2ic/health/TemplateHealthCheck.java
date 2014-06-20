package com.github.ko2ic.health;

import com.codahale.metrics.health.HealthCheck;
import com.github.ko2ic.core.Template;
import com.google.common.base.Optional;

public class TemplateHealthCheck extends HealthCheck {
	private final Template template;

	public TemplateHealthCheck(Template template) {
		this.template = template;
	}

	@Override
	protected Result check() throws Exception {
		return Result.unhealthy(String.format("error=%s",
				template.render(Optional.of("error"))));
	}
}
