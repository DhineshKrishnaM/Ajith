package com.sms.project.utility;

import com.sms.project.subcription.entity.Subscription;
import com.sms.project.subcription.repository.SubscriptionRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Slf4j
public class EmailService {
    @Autowired
    SubscriptionRepo subscriptionRepo;

    private JavaMailSender javaMailSender;

    public void mailForMessage(String toEmail, String body, String subject) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("birunesh@gmail.com");
        simpleMailMessage.setTo(toEmail);
        simpleMailMessage.setText(body);
        simpleMailMessage.setSubject(subject);
        javaMailSender.send(simpleMailMessage);
        log.info("Mail sent to " + toEmail);
    }

    /****
     *
     * Scheduler for expiration mail and set the expiration as well
     */
    @Scheduled(cron = "0 0 0 * * *")
    public void checkExpiryOfAuctions() {
        log.debug("Scheduled task Executed!");
        List<Subscription> subscriptionList = subscriptionRepo.findAll();
        subscriptionList.stream().forEach(subscription -> {
            LocalDate localDate = LocalDate.now();
            if (localDate.equals(subscription.getEndDate().minusDays(2))) {
                mailForMessage(subscription.getUser().getEmail(), "2 Days More to subscription to be expired", "Expiration should update");
            }
        });
        subscriptionList.stream().forEach(subscription -> {
            LocalDate local = LocalDate.now();
            if (local.equals(subscription.getEndDate())) {
                subscription.setStatus("Expired");
                subscriptionRepo.save(subscription);
            }
        });

    }


}
