<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="styles.css">
    
</head>
<body>
    <header>
        <h1>Login</h1>
    </header>
    <main>
    
    <form action="login" method="post" class="update-form"> 
        <table>
        <tr>
           <td> <label for="loginId">Email:</label></td>
           <td><input type="email" id="loginId" name="loginId" placeholder="Login Id" required><br><br></td> 
        </tr>       
        <tr>
        	<td><label for="password">Password:</label></td>
           <td><input type="password" id="password" name="password" placeholder="Valid Password" required><br><br></td>
        </tr>        
        </table>        
        <div class="form-actions">                
                <button type="submit">login</button>
        </div>
    </form>
    
    </main>
    </body>
</html>
	