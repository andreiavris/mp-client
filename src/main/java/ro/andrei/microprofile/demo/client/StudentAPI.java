package ro.andrei.microprofile.demo.client;

import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import ro.andrei.microprofile.demo.client.dto.Student;

@RegisterRestClient
@ApplicationScoped
public interface StudentAPI {

    @GET
    @Path("/{id}")
    Student getById(@PathParam("id") String id);

    @GET
    List<Student> getAll();
}
