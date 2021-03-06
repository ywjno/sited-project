package io.sited.web.impl.controller;

import com.google.common.io.Files;
import io.sited.resource.Resource;
import io.sited.util.MediaTypes;
import io.sited.web.WebRoot;

import javax.inject.Inject;
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.EntityTag;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import java.util.Optional;

/**
 * @author chi
 */
public abstract class WebResourceController {
    @Inject
    WebRoot webRoot;

    protected Response resource(String path, Request request) {
        Optional<Resource> resourceOptional = webRoot.get(path);
        if (!resourceOptional.isPresent()) {
            return Response.ok().status(Response.Status.NOT_FOUND).build();
        }
        Resource resource = resourceOptional.get();
        EntityTag eTag = new EntityTag(resource.hash());
        Response.ResponseBuilder builder = request.evaluatePreconditions(eTag);
        if (builder != null) {
            return builder.build();
        }
        builder = Response.ok(resource).type(MediaTypes.getMediaType(Files.getFileExtension(path)));
        builder.tag(eTag);
        CacheControl cacheControl = new CacheControl();
        cacheControl.setMustRevalidate(true);
        builder.cacheControl(cacheControl);
        return builder.build();
    }

    protected Response resource(String path) {
        Optional<Resource> resource = webRoot.get(path);
        if (!resource.isPresent()) {
            return Response.ok().status(Response.Status.NOT_FOUND).build();
        }
        Response.ResponseBuilder builder = Response.ok(resource.get()).type(MediaTypes.getMediaType(path));
        CacheControl cacheControl = new CacheControl();
        cacheControl.setMaxAge(Integer.MAX_VALUE);
        builder.cacheControl(cacheControl);
        return builder.build();
    }
}
