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

	#Assorted PVs
	#Borf status
	borf = PV('BS_RF_LLRF:S_RF_STATUS')
	#Borf Freq
	bfreq = PV('BS_RF_LLRF:CAV_PHASE_MONITOR')
	srfreq = PV('SR07RF01LLE01:MPHASE_REF_RD_BACK_MONITOR')
	#Ramping magnets
	rmps1 = PV('PS-QD-2:RAMP_DOWNLOAD_STATUS')
	rmps2 = PV('PS-QF-2:RAMP_DOWNLOAD_STATUS')
	rmps3 = PV('PS-BF-2:RAMP_DOWNLOAD_STATUS')
	rmps4 = PV('PS1-BD-2:RAMP_DOWNLOAD_STATUS')
	rmps5 = PV('PS2-BD-2:RAMP_DOWNLOAD_STATUS')
	rmps6 = PV('PS-OCH-B-2-6:RAMP_DOWNLOAD_STATUS')
	rmps7 = PV('PS-OCH-B-2-18:RAMP_DOWNLOAD_STATUS')
	rmps8 = PV('PS-OCH-B-2-22:RAMP_DOWNLOAD_STATUS')
	
	#Kickers	
	brk1 = PV('PS-KI-2:POWERSUPPLY_STATUS3')
	brk2 = PV('PS-KE-2:POWERSUPPLY_STATUS3')

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
	bV9 = PV('PS-OCV-B-2-09:STATUS1')
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

	#Storage ring PVS to collect
	#Shutters
	
	sh1 = PV('PP00:MASTER_SHUTTER_ENABLE_STATUS')
	sh2 = PV('SR02IR01MIR01:POSITION_STATUS')
	sh3 = PV('PP00SR03FE01PSH01:CLOSED_STATUS')
	sh32 = PV('PP00SR03FE02PSH01:CLOSED_STATUS')
	sh4 = PV('PP00SR05FE01PSH01:CLOSED_STATUS')
	sh5 = PV('PP00SR08FE01PSH01:CLOSED_STATUS')
	sh6 = PV('PP00SR10FE01PSH01:CLOSED_STATUS')
	sh7 = PV('PP00SR12FE01PSH01:CLOSED_STATUS')
	sh8 = PV('PP00SR13FE01PSH01:CLOSED_STATUS')
	sh9 = PV('PP00SR14FE01PSH01:CLOSED_STATUS')

	#Gaps/field strengths
	mx2Gap = PV('SR03ID01:GAP_MONITOR')
	xfmGap = PV('SR05ID01:GAP_MONITOR')
	swxGap = PV('SR13ID01:GAP_MONITOR')

	imblField = PV('SR08SCW01:FIELD_MONITOR')

	xasGap = PV('SR12ID01:GAP_MONITOR')
	app2Gap = PV('SR14ID01:GAP_MONITOR')

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

			self.freqdif = str(SimpleEcho.srfreq.get() - SimpleEcho.bfreq.get())[:6]
			self.rmps = str(SimpleEcho.rmps1.get()+SimpleEcho.rmps2.get()+SimpleEcho.rmps3.get()+SimpleEcho.rmps4.get()+SimpleEcho.rmps5.get()+SimpleEcho.rmps6.get()+SimpleEcho.rmps7.get()+SimpleEcho.rmps8.get())

			#Booster Magnets
			self.booDSum = str(SimpleEcho.bD1.get()+SimpleEcho.bD2.get()+SimpleEcho.bD3.get())
			self.booHSum = str(SimpleEcho.bH1.get()+SimpleEcho.bH2.get()+SimpleEcho.bH3.get()+SimpleEcho.bH4.get()+SimpleEcho.bH5.get()+SimpleEcho.bH6.get()+SimpleEcho.bH7.get()+SimpleEcho.bH8.get()+SimpleEcho.bH9.get()+SimpleEcho.bH10.get()+SimpleEcho.bH11.get()+SimpleEcho.bH12.get())
			self.booVSum = str(SimpleEcho.bV1.get()+SimpleEcho.bV2.get()+SimpleEcho.bV3.get()+SimpleEcho.bV4.get()+SimpleEcho.bV5.get()+SimpleEcho.bV6.get()+SimpleEcho.bV7.get()+SimpleEcho.bV8.get()+SimpleEcho.bV9.get()+SimpleEcho.bV10.get()+SimpleEcho.bV11.get()+SimpleEcho.bV12.get())
			self.booQSum = str(SimpleEcho.bQ1.get()+SimpleEcho.bQ2.get())
			self.booSSum = str(SimpleEcho.bS1.get()+SimpleEcho.bS2.get())

		

			#BTS Magnets
			self.btsDSum = str(SimpleEcho.btsD1.get()+SimpleEcho.btsD2.get()+SimpleEcho.btsD3.get()+SimpleEcho.btsD4.get()+SimpleEcho.btsD5.get()+SimpleEcho.btsD6.get())
			self.btsVSum = str(SimpleEcho.btsV1.get()+SimpleEcho.btsV2.get()+SimpleEcho.btsV3.get()+SimpleEcho.btsV4.get()+SimpleEcho.btsV5.get())
			self.btsQSum = str(SimpleEcho.btsQ1.get()+SimpleEcho.btsQ2.get()+SimpleEcho.btsQ3.get()+SimpleEcho.btsQ4.get()+SimpleEcho.btsQ5.get()+SimpleEcho.btsQ6.get()+SimpleEcho.btsQ7.get()+SimpleEcho.btsQ8.get()+SimpleEcho.btsQ9.get()+SimpleEcho.btsQ10.get()+SimpleEcho.btsQ11.get()+SimpleEcho.btsQ12.get())

			self.message = "booster" + "::" + str(SimpleEcho.borf.get()) + "::" + self.freqdif + "::" + self.rmps + "::" + self.booDSum + "::" + self.booQSum + "::" + self.booSSum + "::" + self.booHSum + "::" + self.booVSum + "::" + self.btsDSum + "::" + self.btsQSum + "::" + self.btsVSum

		elif str(self.data) == "storagering":

			self.message = "storagering" + "::" + str(SimpleEcho.sh1.get()) + "::" + str(SimpleEcho.sh2.get()) + "::" + str(SimpleEcho.sh3.get()) + "::" + str(SimpleEcho.sh32.get()) + "::" + str(SimpleEcho.sh4.get()) + "::" + str(SimpleEcho.sh5.get()) + "::" + str(SimpleEcho.sh6.get()) + "::" + str(SimpleEcho.sh7.get()) + "::" + str(SimpleEcho.sh8.get()) + "::" + str(SimpleEcho.sh9.get()) + "::" + str(SimpleEcho.mx2Gap.get())[:5] + " mm" + "::" + str(SimpleEcho.xfmGap.get())[:5] + " mm" + "::" + str(SimpleEcho.imblField.get())[:5] + " T" + "::" + str(SimpleEcho.xasGap.get())[:5] + " mm" + "::" + str(SimpleEcho.swxGap.get())[:5] + " mm" + "::" + str(SimpleEcho.app2Gap.get())[:5] + " mm"

			#print self.message

		self.sendMessage(unicode(self.message,"utf-8"))

 	def handleConnected(self):
 	       print self.address, 'connected'

	def handleClose(self):
	        print self.address, 'closed'

server = SimpleWebSocketServer('', 6000, SimpleEcho)
server.serveforever()
