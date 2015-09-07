package cz.junak.restdi.everguice;

import com.google.inject.Module;
import org.everrest.guice.servlet.EverrestGuiceContextListener;

import java.util.ArrayList;
import java.util.List;

public class ApplicationListener extends EverrestGuiceContextListener{

    @Override
    protected List<Module> getModules() {
        List<Module> modules = new ArrayList<>(1);
        modules.add(new EverGuiceModule());
        return modules;
    }
}

