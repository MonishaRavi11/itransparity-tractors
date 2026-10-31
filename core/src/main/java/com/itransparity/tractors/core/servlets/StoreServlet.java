package com.itransparity.tractors.core.servlets;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.service.component.annotations.Component;
import javax.servlet.Servlet;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component(service = Servlet.class, property = {
        "sling.servlet.paths=/bin/itransparity/stores",
        "sling.servlet.methods=GET"
})
public class StoreServlet extends SlingSafeMethodsServlet {

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException {
        String region = request.getParameter("region");
        if (region == null) region = "";
        region = region.toLowerCase().trim();

        List<String[]> stores = new ArrayList<>();

        if (region.contains("london") || region.contains("uk")) {
            stores.add(new String[]{"Thames Tractors", "Thames Road, London, UK"});
            stores.add(new String[]{"CityFarm Equipment", "City Road, London, UK"});
            stores.add(new String[]{"Kensington Agri", "Kensington, London, UK"});
            stores.add(new String[]{"Docklands Motors", "Docklands, London, UK"});
            stores.add(new String[]{"Greenwich Farm Co.", "Greenwich, London, UK"});
        } else if (region.contains("nairobi") || region.contains("kenya") || region.contains("safari")) {
            stores.add(new String[]{"Safari Agri", "Nairobi, Kenya"});
            stores.add(new String[]{"Nairobi Farm Hub", "Westlands, Nairobi, Kenya"});
            stores.add(new String[]{"Rift Valley Tractors", "Rift Valley, Kenya"});
            stores.add(new String[]{"Mombasa Equipment Ltd.", "Mombasa, Kenya"});
        }
        // Delhi or other -> empty list

        // Build JSON manually without Gson
        StringBuilder json = new StringBuilder("[");
        for (int i = 0; i < stores.size(); i++) {
            String[] s = stores.get(i);
            json.append("{\"name\":\"").append(s[0]).append("\",\"address\":\"").append(s[1]).append("\"}");
            if (i < stores.size() - 1) json.append(",");
        }
        json.append("]");

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.getWriter().write(json.toString());
    }
}