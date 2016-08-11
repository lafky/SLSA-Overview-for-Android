from SimpleWebSocketServer import SimpleWebSocketServer, WebSocket
from epics import caget
from epics import PV

class SimpleEcho(WebSocket):

	#Overview PVs to send back
	bc = PV('SR11BCM01:CURRENT_MONITOR')
	lt = PV('SR11BCM01:LIFETIME_MONITOR')
	mode = PV('TS01FPC01:FILL_STATUS')
	ns  = PV('TS01FPC01:TOPUP_COUNT_DOWN_MONITOR')
	ux = PV('SR00TUM01:X_TUNE_MONITOR')
	uy = PV('SR00TUM01:Y_TUNE_MONITOR')
	br2sr = PV('SR00IE01:INJECTION_EFFICIENCY_MONITOR')
	boocurr = PV('SR00IE01:LATCHED_EXT_CURRENT_MONITOR')
	s1 = PV('SR00IE02:TOPUP_CURRENT_VALUE')
	s2 = PV('SR00IE02:TOPUP_PREVIOUS_VALUE')
	xSize = PV('SR10BM02IMG01:X_SIZE_MONITOR')
	ySize = PV('SR10BM02IMG01:Y_SIZE_MONITOR')
	xOff = PV('SR10BM02IMG01:X_OFFSET_MONITOR')
	yOff = PV('SR10BM02IMG01:Y_OFFSET_MONITOR')
	xSTD = PV('SR10BM02IMG01:X_STD_DEV_MONITOR')
	ySTD = PV('SR10BM02IMG01:Y_STD_DEV_MONITOR')

	#Linac PVs to send back
	#klystron/guns
	k1v = PV('LI-RF-AMPL-01:PFN:HV')
	k2v = PV('LI-RF-AMPL-02:PFN:HV')
	gv = PV('LI-RF-GUN-01:HV')

	#Linac Solenoids
	linS0 = PV('LI-MA-ESS-01:DEVSTATE')
	linS1 = PV('LI-MA-SOL-01:DEVSTATE')
	linS2 = PV('LI-MA-SOL-02:DEVSTATE')
	linS3 = PV('LI-MA-SOL-03:DEVSTATE')
	linS4 = PV('LI-MA-SOL-04:DEVSTATE')
	linS5 = PV('LI-MA-SOL-05:DEVSTATE')
	linS6 = PV('LI-MA-SOL-06:DEVSTATE')
	linS7 = PV('LI-MA-SOL-07:DEVSTATE')
	linS8 = PV('LI-MA-SOL-09:DEVSTATE')
	linS9 = PV('LI-MA-SOL-09:DEVSTATE')
	linS10 = PV('LI-MA-SOL-10:DEVSTATE')
	linS11 = PV('LI-MA-SOL-11:DEVSTATE')
	linS12 = PV('LI-MA-SOL-12:DEVSTATE')
	linS13 = PV('LI-MA-SOL-13:DEVSTATE')
	linS14 = PV('LI-MA-SOL-14:DEVSTATE')
	#Linac Horizontal Correctors
	linH1 = PV('LI-MA-CH-01:DEVSTATE')
	linH2 = PV('LI-MA-CH-02:DEVSTATE')
	linH3 = PV('LI-MA-CH-03:DEVSTATE')
	linH4 = PV('LI-MA-CH-04:DEVSTATE')
	linH5 = PV('LI-MA-CH-05:DEVSTATE')
	linH6 = PV('LI-MA-CH-06:DEVSTATE')
	#Linac Vertical Correctors
	linV1 = PV('LI-MA-CV-01:DEVSTATE')
	linV2 = PV('LI-MA-CV-02:DEVSTATE')
	linV3 = PV('LI-MA-CV-03:DEVSTATE')
	linV4 = PV('LI-MA-CV-04:DEVSTATE')
	linV5 = PV('LI-MA-CV-05:DEVSTATE')
	linV6 = PV('LI-MA-CV-06:DEVSTATE')
	#Linac Quads
	linQ1 = PV('LI-MA-QUAD-01:DEVSTATE')
	linQ2 = PV('LI-MA-QUAD-02:DEVSTATE')
	linQ3 = PV('LI-MA-QUAD-03:DEVSTATE')

	#LTB Diploes
	ltbD1 = PV('PS-B-A-1-1:STATUS1')
	ltbD2 = PV('PS-B-A-1-2:STATUS1')	
	ltbD3 = PV('PS-B-B-1:STATUS1')
	#LTB Horizontal Correctors
	ltbH1 = PV('PS-OCH-A-1-1:STATUS1')
	ltbH2 = PV('PS-OCH-A-1-2:STATUS1')
	#LTB Vertical Correctors
	ltbV1 = PV('PS-OCV-A-1-1:STATUS1')
	ltbV2 = PV('PS-OCV-A-1-2:STATUS1')
	ltbV3 = PV('PS-OCV-A-1-3:STATUS1')
	ltbV4 = PV('PS-OCV-A-1-4:STATUS1')
	#LTB Quads
	ltbQ1 = PV('PS-Q-1-1:STATUS1')
	ltbQ2 = PV('PS-Q-1-2:STATUS1')
	ltbQ3 = PV('PS-Q-1-3:STATUS1')
	ltbQ4 = PV('PS-Q-1-4:STATUS1')
	ltbQ5 = PV('PS-Q-1-5:STATUS1')
	ltbQ6 = PV('PS-Q-1-6:STATUS1')
	ltbQ7 = PV('PS-Q-1-7:STATUS1')
	ltbQ8 = PV('PS-Q-1-8:STATUS1')
	ltbQ9 = PV('PS-Q-1-9:STATUS1')
	ltbQ10 = PV('PS-Q-1-10:STATUS1')
	ltbQ11 = PV('PS-Q-1-11:STATUS1')

	#Booster PVs to send back
	#Dipoles
	bD1 = PV('PS-BF-2:STATUS1')
	bD2 = PV('PS1-BD-2:STATUS1')
	bD3 = PV('PS2-BD-2:STATUS1')

	#Hor correctors
	bH1 = PV('PS-OCH-B-2-1:STATUS1')
	bH2 = PV('PS-OCH-B-2-2:STATUS1')
	bH3 = PV('PS-OCH-B-2-3:STATUS1')
	bH4 = PV('PS-OCH-B-2-4:STATUS1')
	bH5 = PV('PS-OCH-B-2-5:STATUS1')
	bH6 = PV('PS-OCH-B-2-6:STATUS1')
	bH7 = PV('PS-OCH-B-2-7:STATUS1')
	bH8 = PV('PS-OCH-B-2-8:STATUS1')
	bH9 = PV('PS-OCH-B-2-9:STATUS1')
	bH10 = PV('PS-OCH-B-2-10:STATUS1')
	bH11 = PV('PS-OCH-B-2-11:STATUS1')
	bH12 = PV('PS-OCH-B-2-12:STATUS1')

	#Ver corrector
	bV1 = PV('PS-OCV-B-2-1:STATUS1')
	bV2 = PV('PS-OCV-B-2-2:STATUS1')
	bV3 = PV('PS-OCV-B-2-3:STATUS1')
	bV4 = PV('PS-OCV-B-2-4:STATUS1')
	bV5 = PV('PS-OCV-B-2-5:STATUS1')
	bV6 = PV('PS-OCV-B-2-6:STATUS1')
	bV7 = PV('PS-OCV-B-2-7:STATUS1')
	bV8 = PV('PS-OCV-B-2-8:STATUS1')
	bV9 = PV('PS-OCV-B-2-9:STATUS1')
	bV10 = PV('PS-OCV-B-2-10:STATUS1')
	bV11 = PV('PS-OCV-B-2-11:STATUS1')
	bV12 = PV('PS-OCV-E-2-01:STATUS1')

	#Quads:
	bQ1 = PV('PS-QD-2:STATUS1')
	bQ2 = PV('PS-QF-2:STATUS1')

	#Sextupoles:
	bS1 = PV('PS-SD-2:STATUS1')
	bS2 = PV('PS-SF-2:STATUS1')

	#BTS Magnets
	#Dipoles
	btsD1 = PV('PS-BA-3-1:STATUS1')
	btsD2 = PV('PS-BA-3-2:STATUS1')
	btsD3 = PV('PS-BA-3-3:STATUS1')
	btsD4 = PV('PS-BA-3-4:STATUS1')
	btsD5 = PV('PS-SEP-A-3:STATUS1')
	btsD6 = PV('PS-SEP-B-3:STATUS1')

	#Vert correctors
	btsV1 = PV('PS-OC-3-1:STATUS1')
	btsV2 = PV('PS-OC-3-2:STATUS1')
	btsV3 = PV('PS-OC-3-3:STATUS1')
	btsV4 = PV('PS-OC-3-4:STATUS1')
	btsV5 = PV('PS-OC-3-5:STATUS1')

	#Quads:
	btsQ1 = PV('PS-QFA-3-1:STATUS1')
	btsQ2 = PV('PS-QFA-3-2:STATUS1') 
	btsQ3 = PV('PS-QFB-3-1:STATUS1')
	btsQ4 = PV('PS-QFA-3-3:STATUS1')
	btsQ5 = PV('PS-QFA-3-4:STATUS1')
	btsQ6 = PV('PS-QFA-3-5:STATUS1')
	btsQ7 = PV('PS-QFA-3-6:STATUS1')
	btsQ8 = PV('PS-QFA-3-7:STATUS1')
	btsQ9 = PV('PS-QFB-3-2:STATUS1')
	btsQ10 = PV('PS-QFB-3-3:STATUS1')
	btsQ11 = PV('PS-QFC-3-1:STATUS1')
	btsQ12 = PV('PS-QFA-3-8:STATUS1')

	def handleMessage(self):

		if str(self.data) == "overview":
	
			self.message = "overview" + "::" + str(SimpleEcho.bc.get())[:6] + "::" + str(SimpleEcho.lt.get())[:6] + "::" + str(SimpleEcho.mode.get(as_string=True))[:6]  + "::" + str(SimpleEcho.ns.get())[:6] + "::" + str(SimpleEcho.ux.get())[:6] + "::" + str(SimpleEcho.uy.get())[:6] + "::" + str(SimpleEcho.br2sr.get())[:4] + "::" + str(SimpleEcho.boocurr.get())[:4] + "::" + str(SimpleEcho.s1.get())[:6] + "::" + str(SimpleEcho.s2.get())[:6] + "::" + str(SimpleEcho.xSize.get())[:5] + "::" + str(SimpleEcho.ySize.get())[:5] + "::" + str(SimpleEcho.xOff.get())[:5] + "::" +str(SimpleEcho.yOff.get())[:4] + "::" + str(SimpleEcho.xSTD.get())[:5] + "::" + str(SimpleEcho.ySTD.get())[:5]
		elif str(self.data) == "linac":

			#Linac PVs to send back	
			self.k1V = str(caget("LI-RF-AMPL-01:PFN:HV"))[:5]
			self.k2V = str(caget("LI-RF-AMPL-02:PFN:HV"))[:5]
			self.gV = str(caget("LI-RF-GUN-01:HV"))[:5]

			#Sum Signals for Linac and LTB magnets are the sum of all the PV "good" states.  If the sum is > 0 there is a problem.
			#Linac Magnets
			self.linSSum = str(int(SimpleEcho.linS0.get())+int(SimpleEcho.linS1.get())+int(SimpleEcho.linS2.get())+int(SimpleEcho.linS3.get())+int(SimpleEcho.linS4.get())+int(SimpleEcho.linS5.get())+int(SimpleEcho.linS6.get())+int(SimpleEcho.linS7.get())+int(SimpleEcho.linS8.get())+int(SimpleEcho.linS9.get())+int(SimpleEcho.linS10.get())+int(SimpleEcho.linS11.get())+int(SimpleEcho.linS12.get())+int(SimpleEcho.linS13.get())+int(SimpleEcho.linS14.get()))
			self.linHSum = str(int(SimpleEcho.linH1.get())+int(SimpleEcho.linH2.get())+int(SimpleEcho.linH3.get())+int(SimpleEcho.linH4.get())+int(SimpleEcho.linH5.get())+int(SimpleEcho.linH6.get()))
			self.linVSum = str(int(SimpleEcho.linV1.get())+int(SimpleEcho.linV2.get())+int(SimpleEcho.linV3.get())+int(SimpleEcho.linV4.get())+int(SimpleEcho.linV5.get())+int(SimpleEcho.linV6.get()))
			self.linQSum = str(int(SimpleEcho.linQ1.get())+int(SimpleEcho.linQ2.get())+int(SimpleEcho.linQ3.get()))

			#LTB Magnets
			self.ltbDSum = str(int(SimpleEcho.ltbD1.get())+int(SimpleEcho.ltbD2.get())+int(SimpleEcho.ltbD3.get()))
			self.ltbHSum = str(int(SimpleEcho.ltbH1.get())+int(SimpleEcho.ltbH2.get()))
			self.ltbVSum = str(int(SimpleEcho.ltbV1.get())+int(SimpleEcho.ltbV2.get())+int(SimpleEcho.ltbV3.get())+int(SimpleEcho.ltbV4.get()))
			self.ltbQSum = str(int(SimpleEcho.ltbQ1.get())+int(SimpleEcho.ltbQ2.get())+int(SimpleEcho.ltbQ3.get())+int(SimpleEcho.ltbQ4.get())+int(SimpleEcho.ltbQ5.get())+int(SimpleEcho.ltbQ6.get())+int(SimpleEcho.ltbQ7.get())+int(SimpleEcho.ltbQ8.get())+int(SimpleEcho.ltbQ9.get())+int(SimpleEcho.ltbQ10.get())+int(SimpleEcho.ltbQ11.get()))

			self.message = "linac" + "::" + self.k1V + "::" + self.k2V + "::" + self.gV + "::" + self.linSSum + "::" + self.linHSum + "::" + self.linVSum + "::" + self.linQSum + "::" + self.ltbDSum + "::" + self.ltbHSum + "::" + self.ltbVSum + "::" + self.ltbQSum

		elif str(self.data) == "booster":
			
			#Booster Magnets
			self.booDSum = str(int(SimpleEcho.bD1.get())+int(SimpleEcho.bD2.get())+int(SimpleEcho.bD3.get()))
			self.booHSum = str(int(SimpleEcho.bH1.get())+int(SimpleEcho.bH2.get())+int(SimpleEcho.bH3.get())+int(SimpleEcho.bH4.get())+int(SimpleEcho.bH5.get())+int(SimpleEcho.bH6.get())+int(SimpleEcho.bH7.get())+int(SimpleEcho.bH8.get())+int(SimpleEcho.bH9.get())+int(SimpleEcho.bH10.get())+int(SimpleEcho.bH11.get())+int(SimpleEcho.bH12.get()))
			self.booVSum = str(int(SimpleEcho.bV1.get())+int(SimpleEcho.bV2.get())+int(SimpleEcho.bV3.get())+int(SimpleEcho.bV4.get())+int(SimpleEcho.bV5.get())+int(SimpleEcho.bV6.get())+int(SimpleEcho.bV7.get())+int(SimpleEcho.bV8.get())+int(SimpleEcho.bV9.get())+int(SimpleEcho.bV10.get())+int(SimpleEcho.bV11.get())+int(SimpleEcho.bV12.get()))
			self.booQSum = str(int(SimpleEcho.bQ1.get())+int(SimpleEcho.bQ2.get()))
			self.booSSum = str(int(SimpleEcho.bS1.get())+int(SimpleEcho.bS2.get()))

			#BTS Magnets
			self.btsDSum = str(int(SimpleEcho.btsD1.get())+int(SimpleEcho.btsD2.get())+int(SimpleEcho.btsD3.get())+int(SimpleEcho.btsD4.get())+int(SimpleEcho.btsD5.get())+int(SimpleEcho.btsD6.get()))
			self.btsVSum = str(int(SimpleEcho.btsV1.get())+int(SimpleEcho.btsV1.get())+int(SimpleEcho.btsV1.get())+int(SimpleEcho.btsV1.get())+int(SimpleEcho.btsV1.get()))
			self.btsQSum = str(int(SimpleEcho.btsQ1.get())+int(SimpleEcho.btsQ2.get())+int(SimpleEcho.btsQ3.get())+int(SimpleEcho.btsQ4.get())+int(SimpleEcho.btsQ5.get())+int(SimpleEcho.btsQ6.get())+int(SimpleEcho.btsQ7.get())+int(SimpleEcho.btsQ8.get())+int(SimpleEcho.btsQ9.get())+int(SimpleEcho.btsQ10.get())+int(SimpleEcho.btsQ11.get())+int(SimpleEcho.btsQ12.get()))

		self.sendMessage(unicode(self.message,"utf-8"))

 	def handleConnected(self):
 	       print self.address, 'connected'

	def handleClose(self):
	        print self.address, 'closed'

server = SimpleWebSocketServer('', 6000, SimpleEcho)
server.serveforever()
