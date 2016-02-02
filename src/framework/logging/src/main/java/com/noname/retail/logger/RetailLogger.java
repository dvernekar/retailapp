package com.noname.retail.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.LoggerContext;

public class RetailLogger {
	static private Logger _logger = (Logger) LoggerFactory.getLogger(RetailLogger.class);

	/**
	 * This method is called internally by NGLogger class when it finds that the
	 * logger passed by application for logger has not been initialized.
	 * NGLogger does not throw any exception to handle this scenario. It logs
	 * exception message along with stacktrace as a part of handling.
	 */

	static private void handleUninitializedLogger() {
		_logger.error("Uninitialized logger passed by application for logging", new Throwable());
	}

	/**
	 * @param logger
	 *            reference of logger of class calling this method.
	 * @param msg
	 *            message to be logged
	 */
	static public void error(Logger logger, String msg) {
		if (logger == null)
			handleUninitializedLogger();
		else
			logger.error("Msg={}", msg);
	}

	/**
	 * @param logger
	 *            reference of logger of class calling this method.
	 * @param msg
	 *            message to be logged
	 * @param e
	 *            exception to be logged
	 */
	static public void error(Logger logger, String msg, Exception e) {
		if (logger == null)
			handleUninitializedLogger();
		else
			logger.error("Msg=" + msg, e);
	}

	/**
	 * @param logger
	 *            reference of logger of class calling this method.
	 * @param transactionId
	 *            transaction to be logged
	 * @param msg
	 *            message to be logged
	 */
	static public void error(Logger logger, String transactionId, String msg) {
		if (logger == null)
			handleUninitializedLogger();
		else
			logger.error("TRANSID:{}, Msg={}", new String[] { transactionId, msg });
	}

	/**
	 * @param logger
	 *            reference of logger of class calling this method.
	 * @param transactionId
	 *            transaction to be logged
	 * @param msg
	 *            message to be logged
	 * @param e
	 *            exception to be logged
	 */
	static public void error(Logger logger, String transactionId, String msg, Exception e) {
		if (logger == null)
			handleUninitializedLogger();
		else
			logger.error("TRANSID:" + transactionId + ", Msg=" + msg, e);
	}

	/**
	 * @param logger
	 *            reference of logger of class calling this method.
	 * @param transactionId
	 *            transaction to be logged
	 * @param user
	 *            user login name
	 * @param msg
	 *            message to be logged
	 */
	static public void error(Logger logger, String transactionId, String user, String msg) {
		if (logger == null)
			handleUninitializedLogger();
		else
			logger.error("TRANSID:{}, User:{}, Msg={}", new String[] { transactionId, user, msg });
	}

	/**
	 * @param logger
	 *            reference of logger of class calling this method.
	 * @param transactionId
	 *            transaction to be logged
	 * @param user
	 *            user login name
	 * @param msg
	 *            message to be logged
	 * @param e
	 *            exception to be logged
	 */
	static public void error(Logger logger, String transactionId, String user, String msg, Exception e) {
		if (logger == null)
			handleUninitializedLogger();
		else
			logger.error("TRANSID:" + transactionId + ", User:" + user + ", Msg=" + msg, e);
	}

	/**
	 * @param logger
	 *            reference of logger of class calling this method.
	 * @param msg
	 *            message to be logged
	 */
	static public void debug(Logger logger, String msg) {
		if (logger == null)
			handleUninitializedLogger();
		else
			logger.debug("Msg={}", msg);
	}
	
	/**
     * @param logger
     *            reference of logger of class calling this method.
     * @param msg
     *            message to be logged
     * @param e
     *            exception to be logged
     */
    static public void debug(Logger logger, String msg, Exception e) {
        if (logger == null)
            handleUninitializedLogger();
        else
            logger.debug("Msg=" + msg, e);
    }

	/**
	 * @param logger
	 *            reference of logger of class calling this method.
	 * @param transactionId
	 *            transaction to be logged
	 * @param msg
	 *            message to be logged
	 */
	static public void debug(Logger logger, String transactionId, String msg) {
		if (logger == null)
			handleUninitializedLogger();
		else
			logger.debug("TRANSID:{}, Msg={}", new String[] { transactionId, msg });
	}

	/**
	 * @param logger
	 *            reference of logger of class calling this method.
	 * @param transactionId
	 *            transaction to be logged
	 * @param user
	 *            user login name
	 * @param msg
	 *            message to be logged
	 */
	static public void debug(Logger logger, String transactionId, String user, String msg) {
		if (logger == null)
			handleUninitializedLogger();
		else
			logger.debug("TRANSID:{}, User:{}, Msg={}", new String[] { transactionId, user, msg });
	}

	/**
	 * @param logger
	 *            reference of logger of class calling this method.
	 * @param msg
	 *            message to be logged
	 * @throws Exception
	 */
	static public void info(Logger logger, String msg) {
		if (logger == null)
			handleUninitializedLogger();
		else
			logger.info("Msg={}", msg);
	}

	/**
	 * @param logger
	 *            reference of logger of class calling this method.
	 * @param transactionId
	 *            transaction to be logged
	 * @param msg
	 *            message to be logged
	 */
	static public void info(Logger logger, String transactionId, String msg) {
		if (logger == null)
			handleUninitializedLogger();
		else
			logger.info("TRANSID:{}, Msg={}", new String[] { transactionId, msg });
	}

	/**
	 * @param logger
	 *            reference of logger of class calling this method.
	 * @param transactionId
	 *            transaction to be logged
	 * @param user
	 *            user login name
	 * @param msg
	 *            message to be logged
	 */
	static public void info(Logger logger, String transactionId, String user, String msg) {
		if (logger == null)
			handleUninitializedLogger();
		else
			logger.info("TRANSID:{}, User:{}, Msg={}", new String[] { transactionId, user, msg });
	}

	/**
	 * @param logger
	 *            reference of logger of class calling this method.
	 * @param msg
	 *            message to be logged
	 */
	static public void warn(Logger logger, String msg) {
		if (logger == null)
			handleUninitializedLogger();
		else
			logger.warn("Msg={}", msg);
	}
	
	/**
     * @param logger
     *            reference of logger of class calling this method.
     * @param msg
     *            message to be logged
     * @param e
     *            exception to be logged
     */
    static public void warn(Logger logger, String msg, Exception e) {
        if (logger == null)
            handleUninitializedLogger();
        else
            logger.warn("Msg=" + msg, e);
    }

	/**
	 * @param logger
	 *            reference of logger of class calling this method.
	 * @param transactionId
	 *            transaction to be logged
	 * @param msg
	 *            message to be logged
	 */
	static public void warn(Logger logger, String transactionId, String msg) {
		if (logger == null)
			handleUninitializedLogger();
		else
			logger.warn("TRANSID:{}, Msg={}", new String[] { transactionId, msg });
	}

	/**
	 * @param logger
	 *            reference of logger of class calling this method.
	 * @param transactionId
	 *            transaction to be logged
	 * @param user
	 *            user login name
	 * @param msg
	 *            message to be logged
	 */
	static public void warn(Logger logger, String transactionId, String user, String msg) {
		if (logger == null)
			handleUninitializedLogger();
		else
			logger.warn("TRANSID:{}, User:{}, Msg={}", new String[] { transactionId, user, msg });
	}

	/**
	 * @param logger
	 *            reference of logger of class calling this method.
	 * @param msg
	 *            message to be logged
	 */
	static public void trace(Logger logger, String msg) {
		if (logger == null)
			handleUninitializedLogger();
		else
			logger.trace("Msg={}", msg);
	}

	/**
	 * @param logger
	 *            reference of logger of class calling this method.
	 * @param transactionId
	 *            transaction to be logged
	 * @param msg
	 *            message to be logged
	 */
	static public void trace(Logger logger, String transactionId, String msg) {
		if (logger == null)
			handleUninitializedLogger();
		else
			logger.trace("TRANSID:{}, Msg={}", new String[] { transactionId, msg });
	}

	/**
	 * @param logger
	 *            reference of logger of class calling this method.
	 * @param transactionId
	 *            transaction to be logged
	 * @param user
	 *            user login name
	 * @param msg
	 *            message to be logged
	 */
	static public void trace(Logger logger, String transactionId, String user, String msg) {
		if (logger == null)
			handleUninitializedLogger();
		else
			logger.trace("TRANSID:{}, User:{}, Msg={}", new String[] { transactionId, user, msg });
	}

	/**
	 * This method must be called when the application is getting
	 * closed/destroyed. In order to release the resources used by
	 * logback-classic, it is always a good idea to stop the logback context.
	 * Stopping the context will stop all appenders attached to loggers defined
	 * by the context and stop any active threads. This mean all unprocessed
	 * events in the appender's queue will get discarded. Hence if developers
	 * don't want their log events to get discarded then they need to make sure
	 * that they call this method only when all the events in the queue are
	 * processed by appenders (SocketAppender to be more precisely, which is
	 * slow is processing events as it has to sent them over wire to central log
	 * server). To achieve this just call this method after giving a appropriate
	 * sleep/delay duration. If losing unprocessed events is not important then
	 * this can be called without any sleep/delay.
	 */
	public static void stopLoggerContext() {
		LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
		loggerContext.stop();
	}
}
