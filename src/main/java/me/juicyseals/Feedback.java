package me.juicyseals;

import io.sentry.Sentry;
import io.sentry.UserFeedback;
import io.sentry.protocol.SentryId;

public class Feedback {
    public static void suggest(String name, String comments) {
        SentryId sentryId = Sentry.captureMessage("Suggestion");
        UserFeedback userFeedback = new UserFeedback(sentryId);
        userFeedback.setComments(comments);
        userFeedback.setName(name);
        Sentry.captureUserFeedback(userFeedback);
    }

    public static void issue(String name, String comments) {
        SentryId sentryId = Sentry.captureMessage("Issue");
        UserFeedback userFeedback = new UserFeedback(sentryId);
        userFeedback.setComments(comments);
        userFeedback.setName(name);
        Sentry.captureUserFeedback(userFeedback);
    }
}
