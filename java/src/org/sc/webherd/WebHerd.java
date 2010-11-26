package org.sc.webherd;

import java.util.*;
import java.io.*;

public class WebHerd {

    public static void main(String[] args) { 
	String bundleDir = args[0];
	String resourceBase = args.length > 1 ? args[1] : ".";
	int port = 30301;
	WebHerdJettyServer server = new WebHerdJettyServer(port, resourceBase);
    }
}
