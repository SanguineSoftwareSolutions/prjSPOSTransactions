/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.POSTransaction.view;

import com.POSGlobal.controller.clsGlobalVarClass;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class frmPostPOSSalesDataToMMS extends javax.swing.JFrame
{

    String posName = "", posDate = "", time = "";
    private SimpleDateFormat yyyyMMddDateFormat;

    public frmPostPOSSalesDataToMMS()
    {
	initComponents();
	try
	{

	    String bdte = clsGlobalVarClass.gPOSStartDate;
	    SimpleDateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd");
	    Date bDate = dFormat.parse(bdte);
	    String date1 = (bDate.getYear() + 1900) + "-" + (bDate.getMonth() + 1) + "-" + bDate.getDate();
	    time = bDate.getHours() + ":" + bDate.getMinutes() + ":" + bDate.getSeconds();
	    posDate = date1;

	    java.util.Date dt1 = new java.util.Date();
	    int day = dt1.getDate();
	    int month = dt1.getMonth() + 1;
	    int year = dt1.getYear() + 1900;
	    String dte = day + "-" + month + "-" + year;
	    java.util.Date date = new SimpleDateFormat("dd-MM-yyyy").parse(clsGlobalVarClass.gPOSDateToDisplay);
	    dteFromDate.setDate(date);
	    dteToDate.setDate(date);
	    lblPosName.setText(clsGlobalVarClass.gPOSName);
	    lblUserCode.setText(clsGlobalVarClass.gUserCode);
	    lblModuleName.setText(clsGlobalVarClass.gSelectedModule);
	    lblDate.setText(clsGlobalVarClass.gPOSDateToDisplay);
	    yyyyMMddDateFormat = new SimpleDateFormat("yyyy-MM-dd");

	    //cmbPosCode.addItem("All                                         !All");
	    String sql_POS = "select strPosName,strPosCode from tblposmaster";
	    ResultSet rsPOS = clsGlobalVarClass.dbMysql.executeResultSet(sql_POS);
	    while (rsPOS.next())
	    {
		cmbPosCode.addItem(rsPOS.getString(1) + "                                         !" + rsPOS.getString(2));
	    }
	    rsPOS.close();

	}
	catch (Exception e)
	{
	    e.printStackTrace();
	}
    }

    private void funPostData()
    {
	String fromDate = yyyyMMddDateFormat.format(dteFromDate.getDate());
	String toDate = yyyyMMddDateFormat.format(dteToDate.getDate());
	String posCode = cmbPosCode.getSelectedItem().toString().split("!")[1];
	posName = cmbPosCode.getSelectedItem().toString().split("!")[0].trim();

	if (dteToDate.getDate().getTime() >= dteFromDate.getDate().getTime())
	{
	    if (clsGlobalVarClass.gPostSalesDataToMMS)
	    {
		if (clsGlobalVarClass.gEffectOfSales.equalsIgnoreCase("POS"))
		{
		    String res = clsGlobalVarClass.funPostPOSItemSalesData(posCode, fromDate, toDate, "QFile");
		}
		else
		{
		    boolean res = clsGlobalVarClass.funPostItemSalesData(posCode, fromDate, toDate);

		    if (res)
		    {
			JOptionPane.showMessageDialog(null, "Data Post Successfully.");
		    }
		    else
		    {
			JOptionPane.showMessageDialog(null, "Unable to post data.");
		    }
		    return;
		}
	    }
	}
	else
	{
	    JOptionPane.showMessageDialog(null, "Please select valid date");
	    return;
	}
	//dispose();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        panelHeader = new javax.swing.JPanel();
        lblProductName = new javax.swing.JLabel();
        lblModuleName = new javax.swing.JLabel();
        lblformName = new javax.swing.JLabel();
        filler4 = new javax.swing.Box.Filler(new java.awt.Dimension(20, 0), new java.awt.Dimension(20, 0), new java.awt.Dimension(20, 32767));
        filler5 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        lblPosName = new javax.swing.JLabel();
        lblUserCode = new javax.swing.JLabel();
        filler6 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        lblDate = new javax.swing.JLabel();
        lblHOSign = new javax.swing.JLabel();
        panelMainForm = new JPanel() {  
            public void paintComponent(Graphics g) {  
                Image img = Toolkit.getDefaultToolkit().getImage(  
                    getClass().getResource("/com/POSTransaction/images/imgBackgroundImage.png"));  
                g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);  
            }  
        };  
        ;
        panelFormBody = new javax.swing.JPanel();
        lblFormName = new javax.swing.JLabel();
        dteToDate = new com.toedter.calendar.JDateChooser();
        cmbPosCode = new javax.swing.JComboBox();
        lblPOSName = new javax.swing.JLabel();
        btnBack = new javax.swing.JButton();
        btnPostData = new javax.swing.JButton();
        disReportName = new javax.swing.JLabel();
        dteFromDate = new com.toedter.calendar.JDateChooser();
        lblToDate = new javax.swing.JLabel();
        lblFromDate = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setExtendedState(MAXIMIZED_BOTH);
        setMinimumSize(new java.awt.Dimension(800, 600));
        setUndecorated(true);
        addWindowListener(new java.awt.event.WindowAdapter()
        {
            public void windowClosed(java.awt.event.WindowEvent evt)
            {
                formWindowClosed(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt)
            {
                formWindowClosing(evt);
            }
        });

        panelHeader.setBackground(new java.awt.Color(69, 164, 238));
        panelHeader.setLayout(new javax.swing.BoxLayout(panelHeader, javax.swing.BoxLayout.LINE_AXIS));

        lblProductName.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblProductName.setForeground(new java.awt.Color(255, 255, 255));
        lblProductName.setText("SPOS -");
        panelHeader.add(lblProductName);

        lblModuleName.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblModuleName.setForeground(new java.awt.Color(255, 255, 255));
        panelHeader.add(lblModuleName);

        lblformName.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblformName.setForeground(new java.awt.Color(255, 255, 255));
        lblformName.setText("- Post POS Data To CMS");
        panelHeader.add(lblformName);
        panelHeader.add(filler4);
        panelHeader.add(filler5);

        lblPosName.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblPosName.setForeground(new java.awt.Color(255, 255, 255));
        lblPosName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPosName.setMaximumSize(new java.awt.Dimension(321, 30));
        lblPosName.setMinimumSize(new java.awt.Dimension(321, 30));
        lblPosName.setPreferredSize(new java.awt.Dimension(321, 30));
        panelHeader.add(lblPosName);

        lblUserCode.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblUserCode.setForeground(new java.awt.Color(255, 255, 255));
        lblUserCode.setMaximumSize(new java.awt.Dimension(90, 30));
        lblUserCode.setMinimumSize(new java.awt.Dimension(90, 30));
        lblUserCode.setPreferredSize(new java.awt.Dimension(90, 30));
        panelHeader.add(lblUserCode);
        panelHeader.add(filler6);

        lblDate.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblDate.setForeground(new java.awt.Color(255, 255, 255));
        lblDate.setMaximumSize(new java.awt.Dimension(192, 30));
        lblDate.setMinimumSize(new java.awt.Dimension(192, 30));
        lblDate.setPreferredSize(new java.awt.Dimension(192, 30));
        panelHeader.add(lblDate);

        lblHOSign.setMaximumSize(new java.awt.Dimension(34, 30));
        lblHOSign.setMinimumSize(new java.awt.Dimension(34, 30));
        lblHOSign.setPreferredSize(new java.awt.Dimension(34, 30));
        panelHeader.add(lblHOSign);

        getContentPane().add(panelHeader, java.awt.BorderLayout.PAGE_START);

        panelMainForm.setOpaque(false);
        panelMainForm.setLayout(new java.awt.GridBagLayout());

        panelFormBody.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(204, 204, 204), new java.awt.Color(204, 204, 204), new java.awt.Color(153, 153, 153), new java.awt.Color(153, 153, 153)));
        panelFormBody.setMinimumSize(new java.awt.Dimension(800, 570));
        panelFormBody.setOpaque(false);

        cmbPosCode.setBackground(new java.awt.Color(51, 102, 255));
        cmbPosCode.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cmbPosCode.setForeground(new java.awt.Color(255, 255, 255));

        lblPOSName.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblPOSName.setText("POS Name       :");

        btnBack.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnBack.setForeground(new java.awt.Color(255, 255, 255));
        btnBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/POSTransaction/images/imgCommonBtnLong1.png"))); // NOI18N
        btnBack.setText("CLOSE");
        btnBack.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnBack.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/com/POSTransaction/images/imgCommonBtnLong2.png"))); // NOI18N
        btnBack.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                btnBackMouseClicked(evt);
            }
        });
        btnBack.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnBackActionPerformed(evt);
            }
        });

        btnPostData.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnPostData.setForeground(new java.awt.Color(255, 255, 255));
        btnPostData.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/POSTransaction/images/imgCommonBtnLong1.png"))); // NOI18N
        btnPostData.setText("<html>Post Sales<br> Data<br>To MMS</html>");
        btnPostData.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnPostData.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/com/POSTransaction/images/imgCommonBtnLong2.png"))); // NOI18N
        btnPostData.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnPostDataActionPerformed(evt);
            }
        });

        disReportName.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        disReportName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        disReportName.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        lblToDate.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblToDate.setText("To Date          :");

        lblFromDate.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblFromDate.setText("From Date       :");

        javax.swing.GroupLayout panelFormBodyLayout = new javax.swing.GroupLayout(panelFormBody);
        panelFormBody.setLayout(panelFormBodyLayout);
        panelFormBodyLayout.setHorizontalGroup(
            panelFormBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFormBodyLayout.createSequentialGroup()
                .addGroup(panelFormBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelFormBodyLayout.createSequentialGroup()
                        .addGap(311, 311, 311)
                        .addComponent(lblFormName, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelFormBodyLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(disReportName, javax.swing.GroupLayout.PREFERRED_SIZE, 760, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 16, Short.MAX_VALUE))
            .addGroup(panelFormBodyLayout.createSequentialGroup()
                .addGap(221, 221, 221)
                .addGroup(panelFormBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelFormBodyLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(panelFormBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelFormBodyLayout.createSequentialGroup()
                                .addComponent(lblPOSName, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(9, 9, 9)
                                .addComponent(cmbPosCode, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lblFromDate, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelFormBodyLayout.createSequentialGroup()
                        .addComponent(lblToDate, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelFormBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dteToDate, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dteFromDate, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelFormBodyLayout.createSequentialGroup()
                        .addComponent(btnPostData, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelFormBodyLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnBack, btnPostData});

        panelFormBodyLayout.setVerticalGroup(
            panelFormBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelFormBodyLayout.createSequentialGroup()
                .addComponent(disReportName, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblFormName)
                .addGap(35, 35, 35)
                .addGroup(panelFormBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblPOSName, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbPosCode, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(panelFormBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelFormBodyLayout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(dteFromDate, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblFromDate, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(panelFormBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dteToDate, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblToDate, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(53, 53, 53)
                .addGroup(panelFormBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnPostData, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(223, 223, 223))
        );

        panelFormBodyLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnBack, btnPostData});

        panelMainForm.add(panelFormBody, new java.awt.GridBagConstraints());

        getContentPane().add(panelMainForm, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBackMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBackMouseClicked
	// TODO add your handling code here:
	dispose();
	clsGlobalVarClass.hmActiveForms.remove("PostPOSSalesDataToMMS");
    }//GEN-LAST:event_btnBackMouseClicked

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
	// TODO add your handling code here:
    }//GEN-LAST:event_btnBackActionPerformed

    private void btnPostDataActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnPostDataActionPerformed
    {//GEN-HEADEREND:event_btnPostDataActionPerformed
	// TODO add your handling code here:
	funPostData();
    }//GEN-LAST:event_btnPostDataActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt)//GEN-FIRST:event_formWindowClosed
    {//GEN-HEADEREND:event_formWindowClosed
	clsGlobalVarClass.hmActiveForms.remove("PostPOSSalesDataToMMS");
    }//GEN-LAST:event_formWindowClosed

    private void formWindowClosing(java.awt.event.WindowEvent evt)//GEN-FIRST:event_formWindowClosing
    {//GEN-HEADEREND:event_formWindowClosing
	clsGlobalVarClass.hmActiveForms.remove("PostPOSSalesDataToMMS");
    }//GEN-LAST:event_formWindowClosing

    /**
     * @param args the command line arguments
     */
    public static void main(String args[])
    {
	/* Set the Nimbus look and feel */
	//<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
	/* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
	 */
	try
	{
	    for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels())
	    {
		if ("Nimbus".equals(info.getName()))
		{
		    javax.swing.UIManager.setLookAndFeel(info.getClassName());
		    break;
		}
	    }
	}
	catch (ClassNotFoundException ex)
	{
	    java.util.logging.Logger.getLogger(frmPostPOSSalesDataToMMS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	}
	catch (InstantiationException ex)
	{
	    java.util.logging.Logger.getLogger(frmPostPOSSalesDataToMMS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	}
	catch (IllegalAccessException ex)
	{
	    java.util.logging.Logger.getLogger(frmPostPOSSalesDataToMMS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	}
	catch (javax.swing.UnsupportedLookAndFeelException ex)
	{
	    java.util.logging.Logger.getLogger(frmPostPOSSalesDataToMMS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	}
	//</editor-fold>

	/* Create and display the form */
	java.awt.EventQueue.invokeLater(new Runnable()
	{
	    public void run()
	    {
		new frmPostPOSSalesDataToMMS().setVisible(true);
	    }
	});
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnPostData;
    private javax.swing.JComboBox cmbPosCode;
    private javax.swing.JLabel disReportName;
    private com.toedter.calendar.JDateChooser dteFromDate;
    private com.toedter.calendar.JDateChooser dteToDate;
    private javax.swing.Box.Filler filler4;
    private javax.swing.Box.Filler filler5;
    private javax.swing.Box.Filler filler6;
    private javax.swing.JLabel lblDate;
    private javax.swing.JLabel lblFormName;
    private javax.swing.JLabel lblFromDate;
    private javax.swing.JLabel lblHOSign;
    private javax.swing.JLabel lblModuleName;
    private javax.swing.JLabel lblPOSName;
    private javax.swing.JLabel lblPosName;
    private javax.swing.JLabel lblProductName;
    private javax.swing.JLabel lblToDate;
    private javax.swing.JLabel lblUserCode;
    private javax.swing.JLabel lblformName;
    private javax.swing.JPanel panelFormBody;
    private javax.swing.JPanel panelHeader;
    private javax.swing.JPanel panelMainForm;
    // End of variables declaration//GEN-END:variables

}
