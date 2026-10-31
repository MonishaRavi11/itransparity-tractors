package com.itransparity.tractors.core.models;

import com.adobe.cq.dam.cfm.ContentFragment;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class TractorListModel {

    @SlingObject
    private ResourceResolver resolver;

    private List<Tractor> tractors = new ArrayList<>();

    @PostConstruct
    protected void init() {
        String[] paths = {
                "/content/dam/itransparity-tractors/tractor/alpha-450",
                "/content/dam/itransparity-tractors/tractor/beta-550",
                "/content/dam/itransparity-tractors/tractor/gamma-650"
        };

        for(String cfPath : paths) {
            try {
                Resource cfRes = resolver.getResource(cfPath);
                if(cfRes == null) continue;
                ContentFragment cf = cfRes.adaptTo(ContentFragment.class);
                if(cf == null) continue;

                Tractor t = new Tractor();
                t.modelName = getElement(cf, "modelname");
                t.shortDescription = stripHtml(getElement(cf, "shortdescription"));
                t.longDescription = stripHtml(getElement(cf, "longdescription"));
                t.image = getElement(cf, "tractorimage");
                if(t.image == null || t.image.isEmpty()) t.image = "/content/dam/itransparity-tractors/tractor/asset.jpg";
                t.detailPagePath = "/content/itransparity-tractors/us/en/tractors/" + cfRes.getName();
                t.hp = getElement(cf, "hp");
                t.engineType = getElement(cf, "enginetype");

                tractors.add(t);
            } catch(Exception e) {
                // skip
            }
        }
    }

    private String getElement(ContentFragment cf, String name) {
        try {
            if(cf.getElement(name) != null) return cf.getElement(name).getContent();
        } catch(Exception e) {}
        return "";
    }
    private String stripHtml(String s) {
        if(s==null) return "";
        return s.replaceAll("<[^>]*>", "").trim();
    }

    public List<Tractor> getTractors() { return tractors; }

    public static class Tractor {
        public String modelName, shortDescription, longDescription, image, detailPagePath, hp, engineType;
        public String getModelName() { return modelName; }
        public String getShortDescription() { return shortDescription; }
        public String getImage() { return image; }
        public String getDetailPagePath() { return detailPagePath; }
    }
}