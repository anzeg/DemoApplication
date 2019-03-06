package si.test.backend.coding.challenge.interceptors;

import si.test.backend.coding.challenge.service.impl.RequestCounterService;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RequestCounterInterceptor {
    private static Logger logger = Logger.getLogger(RequestCounterInterceptor.class.getName());

    @AroundInvoke
    public Object intercept(InvocationContext ctx) throws Exception
    {
        logger.log(Level.FINE, "RequestCounterInterceptor - before EJB method invoke: "
                + ctx.getMethod().getName());

        try{
            RequestCounterService.add();
        } catch(Exception e){
            logger.log(Level.FINE, "Request counter failed");
            throw e;
        }

        return ctx.proceed();
    }
}
