package com.github.ko2ic;

import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.ko2ic.core.Template;

public class HelloWorldConfiguration extends Configuration {
    @NotEmpty
    private final String template;

    @NotEmpty
    private String defaultName = "Stranger";

    @Valid
    @NotNull
    private DataSourceFactory database = new DataSourceFactory();

    public HelloWorldConfiguration(String template) {
        this.template = template;
    }

    @JsonCreator
    public HelloWorldConfiguration(@JsonProperty("template") String template, @JsonProperty("defaultName") String defaultName) {
        this.template = template;
        this.defaultName = defaultName;
    }

    // @JsonProperty
    public String getTemplate() {
        return template;
    }

    // @JsonProperty
    // public void setTemplate(String template) {
    // this.template = template;
    // }

    @JsonProperty
    public String getDefaultName() {
        return defaultName;
    }

    // @JsonProperty
    // public void setDefaultName(String defaultName) {
    // this.defaultName = defaultName;
    // }

    public Template buildTemplate() {
        return new Template(template, defaultName);
    }

    @JsonProperty("database")
    public DataSourceFactory getDataSourceFactory() {
        return database;
    }

    @JsonProperty("database")
    public void setDataSourceFactory(DataSourceFactory dataSourceFactory) {
        this.database = dataSourceFactory;
    }
}
