import React, { useState } from "react";
import FacultyPanel from "./FacultyPanel";

function App() {

  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  const [token, setToken] = useState(
    localStorage.getItem("token")
  );

  // LOGIN
  const login = async () => {

    try {

      const response = await fetch(
        "https://lms-backend-x287.onrender.com/api/auth/student/login",
        {
          method: "POST",
          headers: {
            "Content-Type": "application/json"
          },
          body: JSON.stringify({
            email: email,
            password: password
          })
        }
      );

      const data = await response.json();

      console.log(data);

      if (response.ok) {

        localStorage.setItem("token", data.token);

        setToken(data.token);

        alert("Login successful");

      } else {

        alert(data.message || "Login failed");
      }

    } catch (error) {

      console.log(error);

      alert("Login failed");
    }
  };

  // LOGOUT
  const logout = () => {

    localStorage.removeItem("token");

    setToken(null);
  };

  // LOGIN PAGE
  if (!token) {

    return (

      <div style={styles.loginContainer}>

        <div style={styles.loginBox}>

          <h1>LMS Login</h1>

          <input
            style={styles.input}
            type="email"
            placeholder="Enter Email"
            onChange={(e) => setEmail(e.target.value)}
          />

          <input
            style={styles.input}
            type="password"
            placeholder="Enter Password"
            onChange={(e) => setPassword(e.target.value)}
          />

          <button style={styles.button} onClick={login}>
            Login
          </button>

        </div>

      </div>
    );
  }

  // DASHBOARD
  return (
    <div>

      <div style={styles.navbar}>

        <h2 style={{ color: "white" }}>LMS Portal</h2>

        <button style={styles.logoutButton} onClick={logout}>
          Logout
        </button>

      </div>

      <FacultyPanel />

    </div>
  );
}

// STYLES
const styles = {

  loginContainer: {
    height: "100vh",
    display: "flex",
    justifyContent: "center",
    alignItems: "center",
    background: "#0f172a"
  },

  loginBox: {
    background: "white",
    padding: "40px",
    borderRadius: "12px",
    width: "350px",
    display: "flex",
    flexDirection: "column",
    gap: "15px"
  },

  navbar: {
    background: "#0f172a",
    padding: "15px 30px",
    display: "flex",
    justifyContent: "space-between",
    alignItems: "center"
  },

  button: {
    padding: "10px 20px",
    border: "none",
    background: "#2563eb",
    color: "white",
    borderRadius: "8px",
    cursor: "pointer"
  },

  logoutButton: {
    padding: "10px 20px",
    border: "none",
    background: "#dc2626",
    color: "white",
    borderRadius: "8px",
    cursor: "pointer"
  },

  input: {
    padding: "12px",
    borderRadius: "8px",
    border: "1px solid #ccc"
  }
};

export default App;