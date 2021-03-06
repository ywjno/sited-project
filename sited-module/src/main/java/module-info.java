/**
 * @author chi
 */
module sited.module {
    requires transitive com.google.common;
    requires transitive java.xml.bind;
    requires transitive aopalliance.repackaged;
    requires transitive javax.annotation.api;
    requires transitive javax.inject;
    requires transitive slf4j.api;
    requires transitive java.validation;
    requires transitive java.ws.rs;
    requires java.activation;

    requires jul.to.slf4j;
    requires jackson.annotations;
    requires jackson.dataformat.yaml;
    requires jackson.core;
    requires jackson.databind;
    requires jackson.datatype.jsr310;
    requires jersey.media.json.jackson;

    requires javassist;
    requires org.objectweb.asm;
    requires jersey.common;
    requires jersey.server;
    requires jersey.hk2;
    requires jersey.bean.validation;
    requires hk2.api;
    requires hk2.utils;
    requires hk2.locator;
    requires org.hibernate.validator;

    exports io.sited;
    exports io.sited.resource;
    exports io.sited.util;
    exports io.sited.util.collection;
    exports io.sited.util.i18n;
    exports io.sited.util.type;
    exports io.sited.util.exception;
}