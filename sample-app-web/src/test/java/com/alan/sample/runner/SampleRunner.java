package com.alan.sample.runner;

import java.io.File;

import org.mortbay.jetty.Connector;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.nio.SelectChannelConnector;
import org.mortbay.jetty.plus.webapp.EnvConfiguration;
import org.mortbay.jetty.webapp.Configuration;
import org.mortbay.jetty.webapp.WebAppContext;

public class SampleRunner {

	private static Server server = new Server();

	public static void main(String[] args) throws Exception {
//        System.setProperty("log.basedir", "c:/logs");
//        System.setProperty("log.appender", "STDOUT");
//		QueuedThreadPool boundedThreadPool = new QueuedThreadPool();
//		boundedThreadPool.setMaxThreads(5);
//		server.setThreadPool(boundedThreadPool);

//		DOMConfigurator.configure("src/test/resources/log4j-web-test.xml");
		
		Connector connector = new SelectChannelConnector();
		connector.setPort(18081);
		server.addConnector(connector);

		WebAppContext context = new WebAppContext("src/main/webapp", "/sample");
		context.setDefaultsDescriptor(new File("src/test/resources/jetty/jetty-descriptor.xml").toURI().getPath());
        EnvConfiguration envConfiguration = new EnvConfiguration();
        envConfiguration.setJettyEnvXml(new File("src/test/resources/jetty/jetty-env.xml").toURI().toURL());
		Configuration[] configurations = new Configuration[]{
		        new org.mortbay.jetty.webapp.WebInfConfiguration(),
		        envConfiguration,
		        new org.mortbay.jetty.plus.webapp.Configuration(),
		        new org.mortbay.jetty.webapp.JettyWebXmlConfiguration(),
		        new org.mortbay.jetty.webapp.TagLibConfiguration()
		};
		context.setConfigurations(configurations);
		server.setHandler(context);
		server.setStopAtShutdown(true);
		server.setSendServerVersion(true);

		server.start();
		System.out.println("============================the server is started===========================");
		server.join();
	}
}