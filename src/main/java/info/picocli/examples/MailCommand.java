package info.picocli.examples;


import org.springframework.stereotype.Component;

import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.concurrent.Callable;

@Component 
@Command(name = "mailCommand")
public class MailCommand implements Callable<Integer> {

    @Autowired private IMailService mailService; 

    @Option(names = "--to", description = "email(s) of recipient(s)", required = true)
    List<String> to;

    @Option(names = "--subject", description = "Subject")
    String subject;

    @Parameters(description = "Message to be sent")
    String[] body = {};

    public Integer call() throws Exception {
        mailService.sendMessage(to, subject, String.join(" ", body)); 
        return 0;
    }
}
