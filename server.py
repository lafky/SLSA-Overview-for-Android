from SimpleWebSocketServer import SimpleWebSocketServer, WebSocket
from epics import caget
from epics import PV

class SimpleEcho(WebSocket):

    def handleMessage(self):
        #Overview PVs to send back
	self.bc = str(caget("SR11BCM01:CURRENT_MONITOR"))[:6]
	self.lt = str(caget("SR11BCM01:LIFETIME_MONITOR"))[:6]
	self.mode = str(caget("TS01FPC01:FILL_STATUS", as_string=True))
	self.ns = str(caget("TS01FPC01:TOPUP_COUNT_DOWN_MONITOR"))[:6]
	
	#Linac PVs to send back
	self.k1V = str(caget("LI-RF-AMPL-01:PFN:HV"))[:5]
	self.k2V = str(caget("LI-RF-AMPL-02:PFN:HV"))[:5]

	if str(self.data) == "overview":
		self.message = "overview" + "::" + self.bc + "::" + self.lt + "::" + self.mode + "::" + self.ns
		#print "test"
	elif str(self.data) == "linac":
		self.message = "linac" + "::" + self.k1V + "::" + self.k2V

	self.sendMessage(unicode(self.message,"utf-8"))

    def handleConnected(self):
        print self.address, 'connected'

    def handleClose(self):
        print self.address, 'closed'

server = SimpleWebSocketServer('', 6000, SimpleEcho)
server.serveforever()
