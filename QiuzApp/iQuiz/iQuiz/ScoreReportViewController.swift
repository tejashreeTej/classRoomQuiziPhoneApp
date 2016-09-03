//
//  ScoreReportViewController.swift
//  iQuiz
//
//  Created by Abhijeet Bhoite on 03/04/16.
//  Copyright © 2016 Tejashree Jagtap. All rights reserved.
//

import UIKit
import Parse

class ScoreReportViewController: UIViewController ,UITableViewDataSource , UITableViewDelegate {
    
    
    @IBOutlet weak var UsernameLabel: UILabel!
    
    private var quizScores = [String]()
    
    
    let reportTableViewIdentifier = "ReportTableViewIdentifier"

    override func viewDidLoad() {
        
        
        super.viewDidLoad()
        print("quizScores viewDidLoad()")
       
        navigationItem.hidesBackButton = true
        
        let query=PFQuery(className: "ScoreReport")
        let currentUser=PFUser.currentUser()
        
        if currentUser != nil{
            let currentUserName = currentUser?.username;
            UsernameLabel.text="User : \(currentUserName!)"
        }
        
        let mainViewControllerObj = self.storyboard?.instantiateViewControllerWithIdentifier("Main") as? MainToolBarViewController
   print("mainViewControllerObj?.mainQuizScores.count = \(mainViewControllerObj?.mainQuizScores.count)")

    }
    
    func loadScoreTableView()
    {
              print("*******loadScoreTableView()******")
        var myReport = String()
        
        let query=PFQuery(className: "ScoreReport")
        let currentUser=PFUser.currentUser()
        
        if currentUser != nil{
            let currentUserName = currentUser?.username;
           // UsernameLabel.text="User : \(currentUserName!)"
            query.whereKey("username", equalTo: currentUserName!)
            
            
            
            query.findObjectsInBackgroundWithBlock { (objects, error) -> Void in
                
                if error == nil {
                    print("error == nil")
                    if let returnedobjects = objects{
                        for var object in returnedobjects{
                            
                            let quizeName = object["quizName"] as! String
                            
                            let quizeScore = object["quizScore"]!
                            
                            myReport = "Quize:\(quizeName) Score :\(quizeScore) \(myReport)"
                            print("\(myReport)")
                            self.quizScores.append(myReport)
                        }
                        
                    }else{
                        print("error")
                        self.UsernameLabel.text="There is No record "
                    }
                    
                }else{
                    self.UsernameLabel.text="There is No record "
                }
                
            }
        }
        print("*******END loadScoreTableView()******")
        print("END loadScoreTableView()")

    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    
    
    func tableView(tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        print("quizScores.count ??")
        //loadScoreTableView()
        print("quizScores.count \(quizScores.count)")
        return quizScores.count
    }
    
    func tableView(tableView: UITableView, cellForRowAtIndexPath indexPath: NSIndexPath) -> UITableViewCell {
        print("cellForRowAtIndexPath")

        var cell=tableView.dequeueReusableCellWithIdentifier(reportTableViewIdentifier)
        if(cell == nil){
            cell = UITableViewCell(style: UITableViewCellStyle.Default, reuseIdentifier: reportTableViewIdentifier)
        }
        
        cell!.textLabel!.text=quizScores[indexPath.row]
        
        return cell!
    }
    
    func tableView(tableView: UITableView, willSelectRowAtIndexPath indexPath: NSIndexPath) -> NSIndexPath? {
         print("willSelectRowAtIndexPath")
        if indexPath.row == 0 {
            return nil
        }else{
            return indexPath
        }
    }
    
    func tableView(tableView: UITableView, didSelectRowAtIndexPath indexPath: NSIndexPath) {
        //let rowValue = quizScores[indexPath.row]
        //let message = "You selected \(rowValue)"
        //selectedCategory="\(rowValue)"
        /*let alert = UIAlertController(title: "Yes I did",
        message: message,
        preferredStyle: .Alert)
        let action = UIAlertAction(title: "OK", style: .Default,
        handler: nil)
        alert.addAction(action)
        self.presentViewController(alert, animated: true, completion: nil)
        */
        
        
    }
    
    func setQuizScores(paramQuizScores:[String]){
        quizScores = paramQuizScores
    }
    
    /*
    // MARK: - Navigation
    
    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepareForSegue(segue: UIStoryboardSegue, sender: AnyObject?) {
    // Get the new view controller using segue.destinationViewController.
    // Pass the selected object to the new view controller.
    }
    */
    
}
