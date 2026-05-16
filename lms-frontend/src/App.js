import React, { useState } from "react";
import FacultyPanel from "./FacultyPanel";

function App() {

  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  const [token, setToken] = useState(
    localStorage.getItem("token")
  );

  const [enrollments, setEnrollments] = useState([]);
  const [attendance, setAttendance] = useState([]);
  const [marks, setMarks] = useState([]);

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


  // COMMON FETCH FUNCTION
  const fetchData = async (url, setter) => {

    try {

      const response = await fetch(
        "https://lms-backend-x287.onrender.com" + url,
        {
          method: "GET",
          headers: {
            Authorization: "Bearer " + token
          }
        }
      );

      const data = await response.json();

      console.log(data);

      if (Array.isArray(data)) {
        setter(data);
      } else {
        setter([]);
        console.log("Response is not array:", data);
      }

    } catch (error) {

      console.log(error);

      setter([]);
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
 return <FacultyPanel />;(

    <div style={styles.dashboard}>

      <div style={styles.navbar}>

        <h2>🎓 LMS Dashboard</h2>

        <button style={styles.logoutButton} onClick={logout}>
          Logout
        </button>

      </div>

      <div style={styles.buttonContainer}>

        <button
          style={styles.button}
          onClick={() =>
            fetchData(
              "/api/student/enrollments",
              setEnrollments
            )
          }
        >
          📚 Enrollments
        </button>

        <button
          style={styles.button}
          onClick={() =>
            fetchData(
              "/api/student/attendance",
              setAttendance
            )
          }
        >
          📅 Attendance
        </button>

        <button
          style={styles.button}
          onClick={() =>
            fetchData(
              "/api/student/marks",
              setMarks
            )
          }
        >
          📊 Marks
        </button>

      </div>

      {/* ENROLLMENTS */}
      <div style={styles.card}>

        <h3>📚 Enrollments</h3>

        {
          enrollments.length === 0 ? (

            <p>No enrollments found</p>

          ) : (

            Array.isArray(enrollments) &&
            enrollments.map((e, index) => (

              <div key={index} style={styles.item}>

                Course ID: {e.course?.id}

              </div>

            ))

          )
        }

      </div>

      {/* ATTENDANCE */}
      <div style={styles.card}>

        <h3>📅 Attendance</h3>

        {
          attendance.length === 0 ? (

            <p>No attendance records</p>

          ) : (

            Array.isArray(attendance) &&
            attendance.map((a, index) => (

              <div key={index} style={styles.item}>

                Status: {a.status}

              </div>

            ))

          )
        }

      </div>

      {/* MARKS */}
      <div style={styles.card}>

        <h3>📊 Marks</h3>

        {
          marks.length === 0 ? (

            <p>No marks available</p>

          ) : (

            Array.isArray(marks) &&
            marks.map((m, index) => (

              <div key={index} style={styles.item}>

                Total: {m.totalMarks}
                {" | Grade: "}
                {m.grade}

              </div>

            ))

          )
        }

      </div>

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

  dashboard: {
    minHeight: "100vh",
    background: "#f1f5f9",
    padding: "30px",
    fontFamily: "Arial"
  },

  navbar: {
    display: "flex",
    justifyContent: "space-between",
    alignItems: "center"
  },

  buttonContainer: {
    marginTop: "20px",
    marginBottom: "20px",
    display: "flex",
    gap: "15px"
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
  },

  card: {
    background: "white",
    padding: "20px",
    marginBottom: "20px",
    borderRadius: "12px",
    boxShadow: "0 2px 8px rgba(0,0,0,0.1)"
  },

  item: {
    padding: "10px",
    borderBottom: "1px solid #eee"
  }
};

export default App;