package com.example.b2c.temple;

/**
 * Created by milk on 2016/10/28.
 */

public class SimpleTemplateModel<T> implements ITemplateModel {
    private T data;
    private Templates templates;

    public SimpleTemplateModel(Templates templates) {
        this.templates = templates;
    }

    public SimpleTemplateModel(Templates templates, T data) {
        this.templates = templates;
        this.data = data;
    }

    public T getData() {
        return data;
    }

    @Override
    public Templates getTemplate() {
        return templates;
    }
}
