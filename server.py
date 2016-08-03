from SimpleWebSocketServer import SimpleWebSocketServer, WebSocket
from epics import caget
from epics import PV

class SimpleEcho(WebSocket):

    def handleMessage(self):

	if str(self.data) == "overview":

		#Overview PVs to send back
		self.bc = str(caget("SR11BCM01:CURRENT_MONITOR"))[:6]
		self.lt = str(caget("SR11BCM01:LIFETIME_MONITOR"))[:6]
		self.mode = str(caget("TS01FPC01:FILL_STATUS", as_string=True))
		self.ns = str(caget("TS01FPC01:TOPUP_COUNT_DOWN_MONITOR"))[:6]
		self.ux = str(caget("SR00TUM01:X_TUNE_MONITOR"))[:6]	
		self.uy = str(caget("SR00TUM01:Y_TUNE_MONITOR"))[:6]
		self.br2sr = str(caget("SR00IE01:INJECTION_EFFICIENCY_MONITOR"))[:4]
		self.boocurr = str(caget("SR00IE01:LATCHED_EXT_CURRENT_MONITOR"))[:4]
		self.s1 = str(caget("SR00IE02:TOPUP_CURRENT_VALUE"))[:4]
		self.s2 = str(caget("SR00IE02:TOPUP_PREVIOUS_VALUE"))[:4]
		self.xSize = str(caget("SR10BM02IMG01:X_SIZE_MONITOR"))[:5]
		self.ySize = str(caget("SR10BM02IMG01:Y_SIZE_MONITOR"))[:5]
		self.xOff = str(caget("SR10BM02IMG01:X_OFFSET_MONITOR"))[:05]
		self.yOff = str(caget("SR10BM02IMG01:Y_OFFSET_MONITOR"))[:04]
		self.xSTD = str(caget("SR10BM02IMG01:X_STD_DEV_MONITOR"))[:05]
		self.ySTD = str(caget("SR10BM02IMG01:Y_STD_DEV_MONITOR"))[:05]

		self.message = "overview" + "::" + self.bc + "::" + self.lt + "::" + self.mode + "::" + self.ns + "::" + self.ux + "::" + self.uy + "::" + self.br2sr + "::" + self.boocurr + "::" + self.s1 + "::" + self.s2 + "::" + self.xSize + "::" + self.ySize + "::" + self.xOff + "::" + self.yOff + "::" + self.xSTD + "::" + self.ySTD
	elif str(self.data) == "linac":

		#Linac PVs to send back	
		self.k1V = str(caget("LI-RF-AMPL-01:PFN:HV"))[:5]
		self.k2V = str(caget("LI-RF-AMPL-02:PFN:HV"))[:5]
		self.gV = str(caget("LI-RF-GUN-01:HV"))[:5]

		#Sum Signals for Linac and LTB magnets are the sum of all the PV "good" states.  If the sum is > 0 there is a problem.
		#Linac Solenoids
		self.linSSum = str(int(caget("LI-MA-ESS-01:DEVSTATE"))+int(caget("LI-MA-SOL-01:DEVSTATE"))+int(caget("LI-MA-SOL-02:DEVSTATE"))+int(caget("LI-MA-SOL-03:DEVSTATE"))+int(caget("LI-MA-SOL-04:DEVSTATE"))+int(caget("LI-MA-SOL-05:DEVSTATE"))+int(caget("LI-MA-SOL-06:DEVSTATE"))+int(caget("LI-MA-SOL-07:DEVSTATE"))+int(caget("LI-MA-SOL-08:DEVSTATE"))+int(caget("LI-MA-SOL-09:DEVSTATE"))+int(caget("LI-MA-SOL-10:DEVSTATE"))+int(caget("LI-MA-SOL-11:DEVSTATE"))+int(caget("LI-MA-SOL-12:DEVSTATE"))+int(caget("LI-MA-SOL-13:DEVSTATE"))+int(caget("LI-MA-SOL-14:DEVSTATE"))+int(caget("LI-MA-SOL-15:DEVSTATE")))
		#Linac Horizontal Correctors
		self.linHSum = str(int(caget("LI-MA-CH-01:DEVSTATE"))+int(caget("LI-MA-CH-02:DEVSTATE"))+int(caget("LI-MA-CH-03:DEVSTATE"))+int(caget("LI-MA-CH-04:DEVSTATE"))+int(caget("LI-MA-CH-05:DEVSTATE"))+int(caget("LI-MA-CH-06:DEVSTATE")))
		#Linac Vertical Correctors
		self.linVSum = str(int(caget("LI-MA-CV-01:DEVSTATE"))+int(caget("LI-MA-CV-02:DEVSTATE"))+int(caget("LI-MA-CV-03:DEVSTATE"))+int(caget("LI-MA-CV-04:DEVSTATE"))+int(caget("LI-MA-CV-05:DEVSTATE"))+int(caget("LI-MA-CV-06:DEVSTATE")))
		#Linac Quads
		self.linQSum = str(int(caget("LI-MA-QUAD-01:DEVSTATE"))+int(caget("LI-MA-QUAD-02:DEVSTATE"))+int(caget("LI-MA-QUAD-03:DEVSTATE")))
		#LTB Magnets
		self.ltbDSum = str(int(caget("PS-B-A-1-1:STATUS1"))+int(caget("PS-B-A-1-2:STATUS1"))+int(caget("PS-B-B-1:STATUS1")))
		self.ltbHSum = str(int(caget("PS-OCH-A-1-1:STATUS1"))+int(caget("PS-OCH-A-1-2:STATUS1")))
		self.ltbVSum = str(int(caget("PS-OCV-A-1-1:STATUS1"))+int(caget("PS-OCV-A-1-2:STATUS1"))+int(caget("PS-OCV-A-1-3:STATUS1"))+int(caget("PS-OCV-A-1-4:STATUS1")))
		self.ltbQSum = str(int(caget("PS-Q-1-1:STATUS1"))+int(caget("PS-Q-1-2:STATUS1"))+int(caget("PS-Q-1-3:STATUS1"))+int(caget("PS-Q-1-4:STATUS1"))+int(caget("PS-Q-1-5:STATUS1"))+int(caget("PS-Q-1-6:STATUS1"))+int(caget("PS-Q-1-7:STATUS1"))+int(caget("PS-Q-1-8:STATUS1"))+int(caget("PS-Q-1-9:STATUS1"))+int(caget("PS-Q-1-10:STATUS1"))+int(caget("PS-Q-1-11:STATUS1")))		

		self.message = "linac" + "::" + self.k1V + "::" + self.k2V + "::" + self.gV + "::" + self.linSSum + "::" + self.linHSum + "::" + self.linVSum + "::" + self.linQSum + "::" + self.ltbDSum + "::" + self.ltbHSum + "::" + self.ltbVSum + "::" + self.ltbQSum

	self.sendMessage(unicode(self.message,"utf-8"))

    def handleConnected(self):
        print self.address, 'connected'

    def handleClose(self):
        print self.address, 'closed'

server = SimpleWebSocketServer('', 6000, SimpleEcho)
server.serveforever()
