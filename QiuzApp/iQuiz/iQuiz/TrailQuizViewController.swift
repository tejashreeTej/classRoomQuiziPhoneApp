//
//  TrailQuizViewController.swift
//  iQuiz
//
//  Created by Abhijeet Bhoite on 06/04/16.
//  Copyright © 2016 Tejashree Jagtap. All rights reserved.
//

import UIKit
import Parse

class TrailQuizViewController: UIViewController,UITableViewDataSource,UITableViewDelegate {
    
    private var quizCategories = [String]()
    var selectedCategory : String!
    
    let simpleTableViewIdentifier = "SimpleTableViewIdentifier"
    
    
    
    override func viewDidLoad() {
        
        super.viewDidLoad()
        print("Hi")
//quizCategories = MainToolBarViewController.getmainQuizCategories()
        //loadTableViewInThread()
          print("TrailQuizViewController")
          //  loadTableView()
        //var n = 0
        //while n<100{
          //  n = n+1
       // }
        /*while (quizCategories.count<1) {
            
        }*/
            print("END TrailQuizViewController")
        // Do any additional setup after loading the view.
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    
    func loadTableView() /*-> Bool*/ {
         print("*** loadTableView() ***")
        let query=PFQuery(className: "Questions")
        query.selectKeys(["category"])
        query.findObjectsInBackgroundWithBlock { (objects, error) -> Void in
            if error == nil {
                
                if let returnedobjects = objects{
                    for object in returnedobjects{
                        print(object["category"] as! String)
                        if self.quizCategories.contains(object["category"] as! String){
                        print("s")
                        }else {
                        self.quizCategories.append(object["category"] as! String)
                        }
                }
                
            }else{
                print("error")
            }
        }
    }
    //return true
        print("O count => \(quizCategories.count)")

        print("*** END loadTableView() ***")

    }
    
    func loadTableViewInThread() /*-> Bool*/ {
                print("loadTableViewInThread")
        let query=PFQuery(className: "Questions")
        query.selectKeys(["category"])
     let objects   = try! query.findObjects()
        
        
  let returnedobjects = objects
            for object in returnedobjects{
                print(object["category"] as! String)
                if self.quizCategories.contains(object["category"] as! String){
                    print("s")
                }else {
                    self.quizCategories.append(object["category"] as! String)
                }
            }
        

      /*  query.findObjectsInBackgroundWithBlock { (objects, error) -> Void in
            if error == nil {
    
                if let returnedobjects = objects{
                    for object in returnedobjects{
                        print(object["category"] as! String)
                        if self.quizCategories.contains(object["category"] as! String){
                            print("s")
                        }else {
                            self.quizCategories.append(object["category"] as! String)
                        }
                    }
                    
                }else{
                    print("error")
                }
            }
        }*/
        //return true
        
    }

func tableView(tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
    print("quizCategories.count => ??")
     //loadTableViewInThread()

       // if(result == true ){
    
    print("quizCategories.count => \(quizCategories.count)")
    //}
        return quizCategories.count
}

func tableView(tableView: UITableView, cellForRowAtIndexPath indexPath: NSIndexPath) -> UITableViewCell {
    var cell=tableView.dequeueReusableCellWithIdentifier(simpleTableViewIdentifier)
    if(cell == nil){
        cell = UITableViewCell(style: UITableViewCellStyle.Default, reuseIdentifier: simpleTableViewIdentifier)
    }
    
    cell!.textLabel!.text=quizCategories[indexPath.row]
    
    return cell!
}

func tableView(tableView: UITableView, willSelectRowAtIndexPath indexPath: NSIndexPath) -> NSIndexPath? {
    if indexPath.row == 0 {
        return nil
    }else{
        return indexPath
    }
}

func tableView(tableView: UITableView, didSelectRowAtIndexPath indexPath: NSIndexPath) {
    let rowValue = quizCategories[indexPath.row]
    let message = "You selected \(rowValue)"
    selectedCategory="\(rowValue)"
    /*let alert = UIAlertController(title: "Yes I did",
    message: message,
    preferredStyle: .Alert)
    let action = UIAlertAction(title: "OK", style: .Default,
    handler: nil)
    alert.addAction(action)
    self.presentViewController(alert, animated: true, completion: nil)
    */
    
    
    print("\(message)")
    
    let quuestionsViewControllerObj = self.storyboard?.instantiateViewControllerWithIdentifier("Question") as? QuestionsViewController
    
    quuestionsViewControllerObj!.quizeCategory.appendContentsOf(rowValue as! String)
    
    self.navigationController?.pushViewController(quuestionsViewControllerObj!, animated: true)
    
}

override func prepareForSegue(segue: UIStoryboardSegue, sender: AnyObject?) {
    print(" prepareForSegue")
    var DestViewController : QuestionsViewController = segue.destinationViewController as! QuestionsViewController
    DestViewController.QNumberLabel.text = selectedCategory
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
