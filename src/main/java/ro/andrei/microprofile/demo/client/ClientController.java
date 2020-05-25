package ro.andrei.microprofile.demo.client;

import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.eclipse.microprofile.metrics.Counter;
import org.eclipse.microprofile.metrics.MetricUnits;
import org.eclipse.microprofile.metrics.annotation.Gauge;
import org.eclipse.microprofile.metrics.annotation.Metric;
import org.eclipse.microprofile.metrics.annotation.Timed;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import ro.andrei.microprofile.demo.client.dto.Student;

@Path("/client")
@ApplicationScoped
public class ClientController {

    @Inject
    @Metric(name = "get_by_id_calls")
    private Counter counter;

    @Inject
    @RestClient
    private StudentAPI studentAPI;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/test/{id}")
    @Timed
    public Student getStudentById(@PathParam("id") String id) {
        counter.inc();
        return studentAPI.getById(id);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/test")
    public List<Student> getAllStudents() {
        return studentAPI.getAll();
    }

    @Gauge(name = "counter_gauge", unit = MetricUnits.NONE)
    private long getCustomerCount() {
        return counter.getCount();
    }
}
