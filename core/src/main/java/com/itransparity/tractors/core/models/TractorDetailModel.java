package com.itransparity.tractors.core.models;

import javax.annotation.PostConstruct;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.Self;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = {SlingHttpServletRequest.class, Resource.class}, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class TractorDetailModel {

    @Self
    private SlingHttpServletRequest request;

    @ValueMapValue
    private String fragmentPath;

    private ValueMap vm;

    @PostConstruct
    protected void init() {
        try {
            if (fragmentPath == null) {
                ValueMap props = request.getResource().getValueMap();
                fragmentPath = props.get("fragmentPath", String.class);
            }
            if (fragmentPath != null) {
                Resource cfRes = request.getResourceResolver().getResource(fragmentPath + "/jcr:content/data/master");
                if (cfRes != null) {
                    vm = cfRes.getValueMap();
                }
            }
        } catch (Exception e) {
            // ignore
        }
    }

    public String getHp() {
        return vm != null ? String.valueOf(vm.get("hp", "")).replace(".0","") : "";
    }
    public String getEnginetype() {
        return vm != null ? vm.get("enginetype", "") : "";
    }
    public String getWeight() {
        return vm != null ? String.valueOf(vm.get("weight", "")).replace(".0","") : "";
    }
    public String getFullcapacity() {
        return vm != null ? String.valueOf(vm.get("fullcapacity", "")).replace(".0","") : "";
    }
    public String getModelname() {
        return vm != null ? vm.get("modelname", "") : "";
    }
    public String getLongdescription() {
        return vm != null ? vm.get("longdescription", "") : "";
    }
    public String getShortdescription() {
        return vm != null ? vm.get("shortdescription", "") : "";
    }
    public String getTractorimage() {
        return vm != null ? vm.get("tractorimage", "") : "";
    }
}