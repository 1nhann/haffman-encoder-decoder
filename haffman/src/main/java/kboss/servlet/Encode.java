package kboss.servlet;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kboss.app.HaffmanTree;
import kboss.app.HaffmanTreeImpl;
import kboss.app.HuffmanTreeBuilder;
import kboss.dsaj.binTree.BinTree;

//10101 to string
public class Encode extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        HaffmanTree haff;
        String useDefault = req.getParameter("use-default");
        req.setAttribute("use-default", useDefault);
        String text = req.getParameter("text-to-encode");
        String charWeightPairs = req.getParameter("encode-weights");
        try{
            if(useDefault == null){haff = new HaffmanTreeImpl(charWeightPairs);}
            else{
                String path = this.getServletContext().getRealPath("data.txt");
                //FileInputStream in=new FileInputStream(path);
                BufferedReader in = new BufferedReader(new FileReader(path));
                charWeightPairs = in.readLine();
                haff = new HaffmanTreeImpl(charWeightPairs);
                in.close();
            }
            String c = haff.encode(text);
            //String haffInfo = haff.showTree();
            req.setAttribute("encoded-text", c);
            //req.setAttribute("haffInfo", haffInfo);
        }
        catch(Exception e){
            req.setAttribute("error", e.getMessage());
        }
        req.setAttribute("text-to-encode", text);
        req.setAttribute("char-weights-pairs", charWeightPairs);
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }
}
