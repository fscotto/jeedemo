package it.plague.jeedemo;

import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("rs")
public class ApplicationConfig extends Application {

  @Override
  public Set<Class<?>> getClasses() {
    Set<Class<?>> resources = new HashSet<>();
    addRestResources(resources);
    return resources;
  }

  private void addRestResources(Set<Class<?>> resources) {
    resources.add(it.plague.jeedemo.BookRestService.class);
    resources.add(org.eclipse.persistence.jaxb.rs.MOXyJsonProvider.class);
  }

}
